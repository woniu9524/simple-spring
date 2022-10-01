package com.woniu.spring.aop;

import java.lang.reflect.Method;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  14:45
 * @Description: 方法匹配，找到表达式范围内匹配下的目标类和方法
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}
