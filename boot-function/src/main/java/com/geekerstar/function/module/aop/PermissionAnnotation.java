package com.geekerstar.function.module.aop;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * @date 2023/4/9 18:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnnotation{
}
