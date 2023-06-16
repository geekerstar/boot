package com.geekerstar.mqtt.module.mqtt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.spring.client.MqttClientTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author geekerstar
 * @date 2023/6/11 13:10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MqttService {

    private final MqttClientTemplate mqttClientTemplate;

    public boolean publish() {
        mqttClientTemplate.publish("/test/client", "Geekerstar".getBytes(StandardCharsets.UTF_8));
        return true;
    }

    public boolean sub() {
        mqttClientTemplate.subQos0("/test/#", (context, topic, message, payload) -> {
            log.info(topic + '\t' + new String(payload, StandardCharsets.UTF_8));
        });
        return true;
    }
}
