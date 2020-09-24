package com.gyb.base.poi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GsExcelProperty {


    int index() default 0;

    String description() default "";

    String format() default "";

    boolean asModel() default true;

    boolean isRequired() default false;

}
