package com.geekerstar.function.module.design_pattern.ifelse;

import com.geekerstar.function.module.design_pattern.ifelse.annotation.IPay;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/8 19:43
 */
@Service
public class PayService3 implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private static final String SUFFIX = "Pay";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void toPay(String payCode) {
        ((IPay) applicationContext.getBean(getBeanName(payCode))).pay();
    }

    public String getBeanName(String payCode) {
        return payCode + SUFFIX;
    }
}
