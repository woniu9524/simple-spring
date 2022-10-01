package com.woniu.spring.aop;


/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  14:39
 * @Description: 切点表达式
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
