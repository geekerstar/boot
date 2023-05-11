package com.geekerstar.tdengine;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2023/5/11 15:31
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class TdengineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TdengineApplication.class, args);
    }
}
