package com.geekerstar.function.module.design_pattern.service_locator_pattern.define;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekerstar
 * @date 2023/4/5 18:15
 */
@Configuration
public class ParserConfig {

    @Bean("parserFactory")
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        // 设置服务定位接口
        factoryBean.setServiceLocatorInterface(ParserFactory.class);
        return factoryBean;
    }

}
