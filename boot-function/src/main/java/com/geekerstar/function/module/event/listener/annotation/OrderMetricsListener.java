package com.geekerstar.function.module.event.listener.annotation;

import com.geekerstar.function.module.event.event.PlaceOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/5 14:19
 */
@Slf4j
@Service("AnnotationOrderMetricsListener")
public class OrderMetricsListener {

    @EventListener
    public void metrics(PlaceOrderEvent event) {
        log.info("注解监听[订单指标]");
    }
}
