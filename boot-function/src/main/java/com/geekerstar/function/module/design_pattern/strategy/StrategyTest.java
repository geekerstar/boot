package com.geekerstar.function.module.design_pattern.strategy;

/**
 * @author geekerstar
 * @date 2023/4/22 12:46
 * https://mp.weixin.qq.com/s/fRjMSZhRvLV8paziI_5YGA
 */
public class StrategyTest {

    public static void main(String[] args) {
        OrderServiceExecutor executor1 = new OrderServiceExecutor(new MySqlSaveOrderStrategyImpl());
        executor1.save("001");
        OrderServiceExecutor executor2 = new OrderServiceExecutor(new NoSqlSaveOrderStrategyImpl());
        executor2.save("002");

        // java8
        OrderServiceExecutor executor3 = new OrderServiceExecutor((String orderNo) -> System.out.println("order:" + orderNo + " save to mysql"));
        executor3.save("001");

        OrderServiceExecutor executor4 = new OrderServiceExecutor((String orderNo) -> System.out.println("order:" + orderNo + " save to nosql"));
        executor4.save("002");
    }
}
