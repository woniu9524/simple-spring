package com.woniu.spring.stereotype;

import java.lang.annotation.*;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-10-01  11:05
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
