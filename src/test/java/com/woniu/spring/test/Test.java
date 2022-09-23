package com.woniu.spring.test;


import com.woniu.spring.beans.context.support.ClassPathXmlApplicationContext;
import com.woniu.spring.test.beans.UserService;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    @org.junit.Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
