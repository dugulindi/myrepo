package com.miaoqian.bigdata.kafka;

import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lind
 * DATETIME 2016/12/1.14:31
 */
public class KafkaConsumer extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    public void run() {
        ConsumerConnector consumer = createConsumer();
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(KafkaConfig.getTopic(), 1); // 一次从主题中获取一个数据
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = messageStreams.get(KafkaConfig.getTopic()).get(0);// 获取每次接收到的这个数据
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            System.out.println(message);
        }
    }

    private ConsumerConnector createConsumer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", KafkaConfig.getZkhosts());//声明zk
        properties.put("auto.offset.reset", "smallest");//
        properties.put("group.id", "error_log_group");// 必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    public static void main(String[] args) {
        new KafkaConsumer().start();// 使用kafka集群中创建好的主题 test

    }

}