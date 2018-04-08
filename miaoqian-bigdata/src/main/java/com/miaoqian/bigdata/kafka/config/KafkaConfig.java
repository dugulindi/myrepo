package com.miaoqian.bigdata.kafka.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KafkaConfig {
    private static String zkhosts;
    private static String topic;
    private static String errorTopic;
    private static String brokers;
    private static String zkServers;
    private static String groupId;

    static {
        Properties prop = new Properties();
        InputStream in = KafkaConfig.class.getClassLoader().getResourceAsStream("kafka.properties");
        try {
            prop.load(in);
            zkhosts = prop.getProperty("zk.hosts").trim();
            topic = prop.getProperty("log.topic").trim();
            errorTopic = prop.getProperty("error.topic").trim();
            brokers = prop.getProperty("metadata.broker.list").trim();
            zkServers = prop.getProperty("zk.servers").trim();
            groupId = prop.getProperty("group.id").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getZkhosts() {
        return zkhosts;
    }

    public static void setZkhosts(String zkhosts) {
        KafkaConfig.zkhosts = zkhosts;
    }

    public static String getTopic() {
        return topic;
    }

    public static void setTopic(String topic) {
        KafkaConfig.topic = topic;
    }

    public static String getBrokers() {
        return brokers;
    }

    public static void setBrokers(String brokers) {
        KafkaConfig.brokers = brokers;
    }

    public static String getErrorTopic() {
        return errorTopic;
    }

    public static void setErrorTopic(String errorTopic) {
        KafkaConfig.errorTopic = errorTopic;
    }

    public static String getZkServers() {
        return zkServers;
    }

    public static void setZkServers(String zkServers) {
        KafkaConfig.zkServers = zkServers;
    }

    public static String getGroupId() {
        return groupId;
    }

    public static void setGroupId(String groupId) {
        KafkaConfig.groupId = groupId;
    }
}
