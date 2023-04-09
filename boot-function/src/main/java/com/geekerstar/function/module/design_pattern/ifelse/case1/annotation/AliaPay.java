package com.geekerstar.function.module.design_pattern.ifelse.case1.annotation;

/**
 * @author geekerstar
 * @date 2023/4/8 19:35
 */

import org.springframework.stereotype.Service;

@PayCode(value = "alia", name = "支付宝支付")
@Service
public class AliaPay implements IPay {

    @Override
    public void pay() {
        System.out.println("===发起支付宝支付===");
    }
}


