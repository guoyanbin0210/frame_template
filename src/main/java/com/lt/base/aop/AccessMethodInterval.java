package com.lt.base.aop;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-03
 * Time: 16:16
 * ‘@interface’ 用来声明注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface AccessMethodInterval {

    int count() default 1;

    long time() default 60*1000;
}
