package com.geekerstar.mqtt;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2023/2/2 16:09
 * https://github.com/lets-mica/mica-mqtt
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class MqttApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }
}
