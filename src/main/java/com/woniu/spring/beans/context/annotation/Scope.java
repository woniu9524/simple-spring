package com.woniu.spring.beans.context.annotation;

import java.lang.annotation.*;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-10-01  11:04
 * @Description: 配置作用域
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
