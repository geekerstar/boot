package com.geekerstar.mqtt.module.mqtt.server;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttPublishMessage;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

import java.nio.charset.StandardCharsets;

/**
 * @author geekerstar
 * @date 2023/2/3 14:25
 */
@Slf4j
@Service
public class MqttServerMessageListener implements IMqttMessageListener {

    @Override
    public void onMessage(ChannelContext context, String clientId, String topic, MqttQoS qoS, MqttPublishMessage message) {
        log.info("clientId:{} topic:{} message:{} payload:{}", clientId,topic, message, new String(message.getPayload(), StandardCharsets.UTF_8));
    }
}
