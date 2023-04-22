package com.geekerstar.function.module.design_pattern.template;

/**
 * @author geekerstar
 * @date 2023/4/22 12:49
 */
public class PushCouponTemplate extends AbstractPushTemplate {

    @Override
    protected void execute(int customerId, String shopName) {
        System.out.println("会员:" + customerId + ",你好，" + shopName + "送您一张优惠券");
    }
}
