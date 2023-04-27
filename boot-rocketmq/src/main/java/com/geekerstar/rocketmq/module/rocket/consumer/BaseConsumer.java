package com.geekerstar.rocketmq.module.rocket.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author geekerstar
 * @date 2023/4/23 20:16
 */
@Component
@RocketMQMessageListener(selectorExpression = "", topic = "base_topic", consumerGroup = "base_group")
@Slf4j
public class BaseConsumer implements RocketMQListener<String> {

    /**
     * 测试接收将参数topic定死，实际开发写入到配置文件
     *
     * @param message
     */
    @Override
    public void onMessage(String message) {
        log.info("基本信息案例-接受到消息:" + message);
    }
}

