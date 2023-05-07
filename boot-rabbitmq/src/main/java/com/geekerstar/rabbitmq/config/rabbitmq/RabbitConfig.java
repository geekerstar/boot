package com.geekerstar.rabbitmq.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekerstar
 * @date 2023/5/7 15:04
 * https://mp.weixin.qq.com/s/zUpM4tVtl_AG9Z5UQZMzrA
 */
@Slf4j
@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{

    /**
     * 定义交换机
     **/
    @Bean
    public DirectExchange directExchange(){
        /**
         * 交换机名称
         * 持久性标志：是否持久化,默认是 true 即声明一个持久的 exchange,该exchange将在服务器重启后继续运行
         * 自动删除标志：是否自动删除，默认为 false, 如果服务器想在 exchange不再使用时删除它，则设置为 true
         **/
        return new DirectExchange("directExchange", true, false);
    }

    /**
     * 定义队列
     **/
    @Bean
    public Queue directQueue(){
        /**
         * name：队列名称
         * durable：是否持久化,默认是 true,持久化队列，会被存储在磁盘上，当消息代理重启时仍然存在
         * exclusive：是否排他，默认为 false，true则表示声明了一个排他队列（该队列将仅由声明者连接使用），如果连接关闭，则队列被删除。此参考优先级高于durable
         * autoDelete：是否自动删除， 默认是 false，true则表示当队列不再使用时，服务器删除该队列
         **/
        return new Queue("directQueue",true);
    }

    /**
     * 队列和交换机绑定
     * 设置路由键：directRouting
     **/
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRouting");
    }

//    @Bean
//    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        //设置开启 Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
//        rabbitTemplate.setMandatory(true);
//
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
//                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
//                System.out.println("ConfirmCallback:     "+"原因："+cause);
//            }
//        });
//
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback(){
//            @Override
//            public void returnedMessage(ReturnedMessage returned) {
//                System.out.println("ReturnCallback:     "+"消息："+returned.getMessage());
//                System.out.println("ReturnCallback:     "+"回应码："+returned.getReplyCode());
//                System.out.println("ReturnCallback:     "+"回应信息："+returned.getReplyText());
//                System.out.println("ReturnCallback:     "+"交换机："+returned.getExchange());
//                System.out.println("ReturnCallback:     "+"路由键："+returned.getRoutingKey());
//            }
//        });
//        return rabbitTemplate;
//    }

    /**
     * 消息服务器返回的basic.ack
     *
     * @param correlationData 关联数据对象
     * @param ack ack
     * @param cause 异常信息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("receive ack confirm：{} from broker server", ack);
    }

    /**
     * 消息服务器返回的basic.return
     *
     * @param message 消息对象
     * @param replyCode 响应code
     * @param replyText 响应文本
     * @param exchange 交换机
     * @param routingKey 路由key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("receive return message：{} from broker server，reply code：{}，reply text：{}，" +
                "exchange：{}，routing key：{}", message.toString(), replyCode, replyText, exchange, routingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(RabbitTemplateConfigurer configurer, ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        configurer.configure(rabbitTemplate, connectionFactory);
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
