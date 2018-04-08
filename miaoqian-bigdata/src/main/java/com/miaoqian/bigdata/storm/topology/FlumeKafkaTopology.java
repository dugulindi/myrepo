package com.miaoqian.bigdata.storm.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import com.miaoqian.bigdata.storm.bolt.logs.LogProcessBolt;
import com.miaoqian.bigdata.storm.bolt.logs.ParseLogInfoBolt;
import com.miaoqian.bigdata.storm.util.MessageScheme;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lind
 * DATETIME 2016/3/29.9:31
 */
public class FlumeKafkaTopology {
    public static void main(String[] args) throws Exception {
        // 配置Zookeeper地址
        BrokerHosts brokerHosts = new ZkHosts("172.20.4.48:2181,172.20.4.49:2181,172.20.4.50:2181");
        // 配置Kafka订阅的Topic，以及zookeeper中数据节点目录和名字
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "kafkalog", "/kafkalog", "kafkaspout");

        // 配置KafkaBolt中的kafka.broker.properties
        Config conf = new Config();
        Map<String, String> map = new HashMap<String, String>();
        // 配置Kafka broker地址
        map.put("metadata.broker.list", "172.20.4.48:9092,172.20.4.49:9092,172.20.4.50:9092");
        // serializer.class为消息的序列化类
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        conf.put("kafka.broker.properties", map);
        // 配置KafkaBolt生成的topic
        conf.put("topic", "resultTopic");

        spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaspout", new KafkaSpout(spoutConfig));
        builder.setBolt("parseLogInfoBolt", new ParseLogInfoBolt()).shuffleGrouping("kafkaspout");
        builder.setBolt("solrInsertBolt", new LogProcessBolt()).shuffleGrouping("parseLogInfoBolt");
        builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("solrInsertBolt");
        //
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("FlumeKafkaTopology", conf, builder.createTopology());
    }
}
