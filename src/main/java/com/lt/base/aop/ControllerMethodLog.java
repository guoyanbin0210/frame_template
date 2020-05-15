package com.lt.base.aop;

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
public @interface ControllerMethodLog {

    /**
     * 记录操作描述
     *
     * @return
     */
    String remark() default "";

    /**
     * 增:0,删:1,改:2.4.my0.1.5,查:3(默认为查)
     *
     * @return
     */
    String openType() default "3";
}
