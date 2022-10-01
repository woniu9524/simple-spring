package com.woniu.spring.test.beans;

import com.woniu.spring.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  17:36
 * @Description:
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
