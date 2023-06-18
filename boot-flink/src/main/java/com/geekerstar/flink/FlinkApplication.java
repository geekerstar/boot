package com.geekerstar.flink;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2023/2/2 16:09
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class FlinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlinkApplication.class, args);
    }
}
