package com.geekerstar.function.module.design_pattern.ifelse.annotation;

import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/8 19:36
 */
@PayCode(value = "weixin", name = "微信支付")
@Service
public class WeixinPay implements IPay {

    @Override
    public void pay() {
        System.out.println("===发起微信支付===");
    }
}
