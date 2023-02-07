package com.geekerstar.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2023/2/7 12:02
 * http://www.websocket-test.com/
 * <p>
 * ws://127.0.0.1:8080/?accessToken=geek
 */
@SpringBootApplication
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
