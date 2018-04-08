package com.miaoqian.bigdata.storm.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import com.miaoqian.bigdata.storm.bolt.logs.LogProcessBolt;
import com.miaoqian.bigdata.storm.util.MessageScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LogTopicTopology extends Thread implements Serializable{
    private final static Logger logger = LoggerFactory.getLogger(LogTopicTopology.class);

    @Override
    public void run() {
        try {
            System.out.println("log process topology is start...");
            BrokerHosts brokerHosts = new ZkHosts(KafkaConfig.getZkhosts());
            // 配置Kafka订阅的Topic，以及zookeeper中数据节点目录和名字
            SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, KafkaConfig.getTopic(), "/" + KafkaConfig.getTopic(), KafkaConfig.getGroupId());
            spoutConfig.useStartOffsetTimeIfOffsetOutOfRange = true;
            spoutConfig.startOffsetTime = kafka.api.OffsetRequest.EarliestTime();
            spoutConfig.zkServers = Arrays.asList(KafkaConfig.getZkServers().split(","));
            spoutConfig.zkPort = 2181;

            // 配置KafkaBolt中的kafka.broker.properties
            Config conf = new Config();
            Map<String, String> map = new HashMap<String, String>();
            // 配置Kafka broker地址
            map.put("metadata.broker.list", KafkaConfig.getBrokers());
            // serializer.class为消息的序列化类
            map.put("serializer.class", "kafka.serializer.StringEncoder");
            map.put("auto.offset.reset", "smallest");
            map.put("group.id", KafkaConfig.getGroupId());
            conf.put("kafka.broker.properties", map);
            // 配置KafkaBolt生成的topic
            conf.put("topic", "resultTopic");
            spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
            TopologyBuilder builder = new TopologyBuilder();
            builder.setSpout(KafkaConfig.getGroupId(), new KafkaSpout(spoutConfig));
//	            builder.setBolt("parseLogInfoBolt", new ParseLogInfoBolt()).shuffleGrouping("kafkaspout");
            builder.setBolt("LogProcessBolt", new LogProcessBolt()).shuffleGrouping(KafkaConfig.getGroupId());
            builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("LogProcessBolt");
            //
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("logProcess", conf, builder.createTopology());
            System.out.println("log process topology is end...");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
