package com.ruoyi.common.constant;

import java.lang.annotation.*;

/**
 * 自定义免登陆注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
}
