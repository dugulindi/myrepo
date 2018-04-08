package com.miaoqian.bigdata.kafka;

/**
 * Created by lind
 * DATETIME 2016/12/1.17:05
 */
public interface KafkaService {
    /**
     * 发消息
     *
     * @param topic 主题
     * @param obj   发送内容
     */
    public void sendMessage(String topic, Object obj);
}
