package com.geekerstar.function.module.design_pattern.ifelse.case1.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2023/4/8 19:37
 */
@Service
public class PayService implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<String, IPay> payMap = null;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);

        payMap = new HashMap<>();
        beansWithAnnotation.forEach((key, value) -> {
            String bizType = value.getClass().getAnnotation(PayCode.class).value();
            payMap.put(bizType, (IPay) value);
        });
    }

    public void pay(String code) {
        payMap.get(code).pay();
    }
}
