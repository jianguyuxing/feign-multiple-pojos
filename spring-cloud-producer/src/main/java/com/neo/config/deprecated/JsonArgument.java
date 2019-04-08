package com.neo.config.deprecated;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-16 18:06
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonArgument {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    //默认取false处理
    boolean required() default false;

    String defaultValue() default ValueConstants.DEFAULT_NONE;

}
