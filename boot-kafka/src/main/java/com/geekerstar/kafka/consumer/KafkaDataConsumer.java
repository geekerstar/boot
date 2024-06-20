/*
 * Copyright (c) emfuture, 2024, Emfuture Technology Co.,Ltd. All rights reserved.
 */
package com.geekerstar.kafka.consumer;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author geekerstar
 * @date 2024/6/20 15:23
 */
@Component
@Slf4j
public class KafkaDataConsumer {
    @KafkaListener(topics = "test", groupId = "test")
    public void topicTest(List<String> messages, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        for (String message : messages) {

            System.out.println("test" + " 消费了： Topic:" + topic + ",Message:" + message);
            //ack.acknowledge();
        }
    }
}
