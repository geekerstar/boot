package com.geekerstar.function.module.design_pattern.strategy;

/**
 * @author geekerstar
 * @date 2023/4/22 12:44
 */
public class NoSqlSaveOrderStrategyImpl implements OrderService {
    @Override
    public void saveOrder(String orderNo) {
        System.out.println("order:" + orderNo + " save to nosql");
    }
}
