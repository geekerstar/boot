package com.geekerstar.function.module.design_pattern.observer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:53
 */
public class OrderObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 状态更新为【已支付】");
    }
}
