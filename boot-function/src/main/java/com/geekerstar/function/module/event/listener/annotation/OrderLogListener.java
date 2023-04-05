package com.geekerstar.function.module.event.listener.annotation;

import com.geekerstar.function.module.event.event.PlaceOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/5 14:17
 */
@Slf4j
@Service("AnnotationOrderLogListener")
public class OrderLogListener {

    @EventListener
    public void orderLog(PlaceOrderEvent event) {
        log.info("注解监听[订单日志]");
    }
}
