package com.geekerstar.function.module.event.publish;

import com.geekerstar.function.module.event.event.PlaceOrderEvent;
import com.geekerstar.function.module.event.event.PlaceOrderEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/5 14:25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublishService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void placeOrder() {
        log.info("发布事件[placeOrder] start.");
        //消息
        PlaceOrderEventMessage eventMessage = new PlaceOrderEventMessage();
        eventMessage.setOrderId("111");
        eventMessage.setOrderStatus(0);
        eventMessage.setUserId("111");

        //发布事件
        applicationEventPublisher.publishEvent(new PlaceOrderEvent(eventMessage));
        log.info("发布事件[placeOrder] end.");
    }

}
