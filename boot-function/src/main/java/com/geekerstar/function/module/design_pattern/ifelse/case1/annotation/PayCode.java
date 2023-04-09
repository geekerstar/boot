package com.geekerstar.function.module.design_pattern.ifelse.case1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author geekerstar
 * @date 2023/4/8 19:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayCode {

    String value();

    String name();
}
