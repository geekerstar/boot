package com.geekerstar.function.module.design_pattern.observer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:54
 * https://mp.weixin.qq.com/s/fRjMSZhRvLV8paziI_5YGA
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        subject.registerObserver(new OrderObserver());
        subject.registerObserver(new StockObserver());
        subject.notifyAllObserver("001");


        NewSubject subject1 = new NewSubject() {
        };
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 状态更新为【已支付】"));
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 已通知库房发货！"));
        subject1.nofityAllObserver("002");
    }
}
