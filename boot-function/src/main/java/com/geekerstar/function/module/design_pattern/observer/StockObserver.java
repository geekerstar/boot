package com.geekerstar.function.module.design_pattern.observer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:53
 */
public class StockObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 已通知库房发货！");
    }
}
