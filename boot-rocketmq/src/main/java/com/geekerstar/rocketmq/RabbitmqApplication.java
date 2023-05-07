package com.geekerstar.rocketmq;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2023/4/23 20:16
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class RabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }
}
