package com.geekerstar.rabbitmq.module.sample.rabbit.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 使用fanout交换器的时候，可以在consumer中省略routing-key的配置。
 * 因为fanout交换器忽略routing-key的匹配，即使配置当type=ExchangeTypes.FANOUT时也无效。
 */
@Component
@RabbitListener(
            bindings=@QueueBinding(
                    value=@Queue(value="${mq.config.queue.sms}",autoDelete="true"),
                    exchange=@Exchange(value="${mq.config.exchange}",type= ExchangeTypes.FANOUT)
            )
        )
public class FanoutConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("Sms........receiver: "+msg);
    }
}
