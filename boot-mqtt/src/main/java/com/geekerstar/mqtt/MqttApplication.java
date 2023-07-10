package com.geekerstar.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2023/2/2 16:09
 * https://github.com/lets-mica/mica-mqtt
 * 证书转换：https://myssl.com/cert_convert.html
 */
//@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@SpringBootApplication
public class MqttApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }
}
