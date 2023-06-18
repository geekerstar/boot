package com.geekerstar.flink.config.log;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * @date 2022/1/13 16:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Weblog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
