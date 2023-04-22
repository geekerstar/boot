package com.geekerstar.function.module.design_pattern.strategy;

/**
 * @author geekerstar
 * @date 2023/4/22 12:45
 */
public class OrderServiceExecutor {

    private final OrderService service;

    public OrderServiceExecutor(OrderService service) {
        this.service = service;
    }

    public void save(String orderNo) {
        this.service.saveOrder(orderNo);
    }

}
