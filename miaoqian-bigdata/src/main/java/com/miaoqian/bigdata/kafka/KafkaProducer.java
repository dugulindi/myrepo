package com.miaoqian.bigdata.kafka;

import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

/**
 * Created by lind
 * DATETIME 2016/12/1.14:26
 */
public class KafkaProducer {

    public void sentMessage(String message) {
        Producer producer = createProducer();
        producer.send(new KeyedMessage<Integer, String>(KafkaConfig.getErrorTopic(), message));
    }

    private Producer createProducer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", KafkaConfig.getZkhosts());//声明zk
        properties.put("serializer.class", StringEncoder.class.getName());
        properties.put("metadata.broker.list", KafkaConfig.getBrokers());// 声明kafka broker
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public static void main(String[] args) {
        new KafkaProducer().sentMessage("cecece");
    }
}
