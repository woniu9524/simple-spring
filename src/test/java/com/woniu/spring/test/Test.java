package com.woniu.spring.test;

import com.woniu.spring.BeanDefinition;
import com.woniu.spring.BeanFactory;
import com.woniu.spring.UserService;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    /*
    * BeanDefinition中保存要实例化的bean的信息，提供getBean方法
    * BeanFactory中用Map保存BeanDefinition，提供注册方法添加BeanDefinition到Map中
    * 以及getBean获取BeanDefinition
    * */
    @org.junit.Test
    public void testBeanDefinition(){
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
