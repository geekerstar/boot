package com.geekerstar.function.module.design_pattern.ifelse.case3.annotation;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * @date 2023/4/9 12:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
    String desc() default "";

    String url() default "";
}
