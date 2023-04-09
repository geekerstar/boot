package com.geekerstar.function.module.design_pattern.ifelse.case1.annotation;

import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/8 19:37
 */
@PayCode(value = "jingdong", name = "京东支付")
@Service
public class JingDongPay implements IPay {

    @Override
    public void pay() {
        System.out.println("===发起京东支付===");
    }
}
