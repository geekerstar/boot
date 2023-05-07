package com.geekerstar.rabbitmq.module.sample.rabbit.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
            bindings=@QueueBinding(
                    value=@Queue(value="log.info",autoDelete="false"),
                    exchange=@Exchange(value="log.topic",type= ExchangeTypes.TOPIC),
                    key="*.log.info"
            )
        )
public class TopicConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("......Info........receiver: "+msg);
    }
}
