package com.geekerstar.rocketmq.module.rocket.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author geekerstar
 * @date 2023/4/23 20:16
 */
@Service
@Slf4j
public class TestProducer {
    /**
     * 测试发送将参数topic定死，实际开发写入到配置文件
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        String text = "测试发送";
        Message<String> message = MessageBuilder.withPayload(text).build();
        log.info("开始发送...");
        rocketMQTemplate.send("test_topic", message);
        log.info("已发送...");
    }
}

