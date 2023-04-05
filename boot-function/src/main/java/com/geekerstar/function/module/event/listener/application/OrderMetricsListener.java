package com.geekerstar.function.module.event.listener.application;

import com.geekerstar.function.module.event.event.PlaceOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/5 14:15
 */
@Slf4j
@Service("ApplicationOrderMetricsListener")
public class OrderMetricsListener implements ApplicationListener<PlaceOrderEvent> {

    @Override
    public void onApplicationEvent(PlaceOrderEvent event) {
        log.info("事件监听[订单指标]");
    }
}
