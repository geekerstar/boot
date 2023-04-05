package com.geekerstar.function.module.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author geekerstar
 * @date 2023/4/5 14:13
 */
public class PlaceOrderEvent extends ApplicationEvent {

    public PlaceOrderEvent(PlaceOrderEventMessage source) {
        super(source);
    }
}
