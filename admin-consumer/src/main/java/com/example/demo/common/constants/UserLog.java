package com.example.demo.common.constants;

import java.lang.annotation.*;

/**
 * 自定义日志记录注解类
 * value为行为
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {
    String value() default "";
}

