package com.geekerstar.mqtt.module.mqtt.service;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.spring.server.event.MqttClientOfflineEvent;
import net.dreamlu.iot.mqtt.spring.server.event.MqttClientOnlineEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/6/11 12:17
 */
@Slf4j
@Service
public class MqttConnectStatusListener {
    @EventListener
    public void online(MqttClientOnlineEvent event) {
        log.info("MQTT客户端上线:{}", event);
    }

    @EventListener
    public void offline(MqttClientOfflineEvent event) {
        log.info("MQTT客户端离线:{}", event);
    }

}
