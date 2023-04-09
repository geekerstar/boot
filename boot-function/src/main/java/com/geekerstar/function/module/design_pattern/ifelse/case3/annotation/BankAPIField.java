package com.geekerstar.function.module.design_pattern.ifelse.case3.annotation;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * @date 2023/4/9 12:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
    int order() default -1;

    int length() default -1;

    String type() default "";
}
