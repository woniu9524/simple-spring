package com.woniu.spring;

import com.woniu.spring.aop.BeforeAdvice;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
