//xxx
package com.woniu.spring.test;


import com.woniu.spring.TargetSource;
import com.woniu.spring.aop.AdvisedSupport;
import com.woniu.spring.aop.aspectj.AspectJExpressionPointcut;
import com.woniu.spring.aop.framework.Cglib2AopProxy;
import com.woniu.spring.aop.framework.JdkDynamicAopProxy;
import com.woniu.spring.beans.context.support.ClassPathXmlApplicationContext;
import com.woniu.spring.test.beans.IUserService;
import com.woniu.spring.test.beans.UserService;
import com.woniu.spring.test.beans.UserServiceInterceptor;
import com.woniu.spring.test.event.CustomEvent;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    @org.junit.Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.woniu.spring.test.beans.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

        // true、true
    }

    @org.junit.Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.woniu.spring.test.beans.UserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }
}
