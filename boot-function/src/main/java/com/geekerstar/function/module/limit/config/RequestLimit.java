package com.geekerstar.function.module.limit.config;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * @date 2023/2/6 16:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequestLimit {
    int count() default 2000;
    int time() default 5 * 1000;
}

