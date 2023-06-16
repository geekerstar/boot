package com.geekerstar.mqtt.module.mqtt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.spring.client.MqttClientSubscribe;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author geekerstar
 * @date 2023/6/11 13:08
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MqttClientSubscribeListener {

    @MqttClientSubscribe("/test/#")
    public void subQos0(String topic, byte[] payload) {
        log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
    }

    @MqttClientSubscribe(value = "/qos1/#", qos = MqttQoS.AT_LEAST_ONCE)
    public void subQos1(String topic, byte[] payload) {
        log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
    }

    @MqttClientSubscribe("/sys/${productKey}/${deviceName}/thing/sub/register")
    public void thingSubRegister(String topic, byte[] payload) {
        // 1.3.8 开始支持，@MqttClientSubscribe 注解支持 ${} 变量替换，会默认替换成 +
        // 注意：mica-mqtt 会先从 Spring boot 配置中替换参数 ${}，如果存在配置会优先被替换。
        log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
    }

}
