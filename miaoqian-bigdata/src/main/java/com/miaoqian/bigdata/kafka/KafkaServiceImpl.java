package com.miaoqian.bigdata.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by lind
 * DATETIME 2016/12/1.17:06
 */
@Service("kafkaService")
public class KafkaServiceImpl implements KafkaService {
    @Override
    public void sendMessage(String topic, Object obj) {

    }

//    @Autowired
//    @Qualifier("kafkaLogTopic")
//    MessageChannel channel;
//
//    public void sendMessage(String topic, Object obj) {
//        channel.send(MessageBuilder.withPayload(obj)
//                .setHeader(KafkaHeaders.TOPIC, topic)
//                .build());
//    }

}
