package com.geekerstar.function.module.design_pattern.template;

/**
 * @author geekerstar
 * @date 2023/4/22 12:51
 * https://mp.weixin.qq.com/s/fRjMSZhRvLV8paziI_5YGA
 */
public class TemplateTest {

    public static void main(String[] args) {
        AbstractPushTemplate template1 = new PushCouponTemplate();
        template1.push(1, "糖果店");

        AbstractPushTemplate template2 = new PushScoreTemplate();
        template2.push(1, "服装店");

        new PushTemplateLambda().push(1, "糖果店", (Object[] obj) -> {
            System.out.println("会员:" + obj[0] + ",你好，" + obj[1] + "送您一张优惠券");
        });

        new PushTemplateLambda().push(1, "服装店", (Object[] obj) -> {
            System.out.println("会员:" + obj[0] + ",你好，" + obj[1] + "送您10个积分");
        });
    }
}
