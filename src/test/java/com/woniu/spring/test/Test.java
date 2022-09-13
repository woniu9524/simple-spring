package com.woniu.spring.test;

import com.woniu.spring.PropertyValue;
import com.woniu.spring.PropertyValues;
import com.woniu.spring.UserDao;
import com.woniu.spring.UserService;
import com.woniu.spring.factory.config.BeanDefinition;
import com.woniu.spring.factory.config.BeanReference;
import com.woniu.spring.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.Constructor;


/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    @org.junit.Test
    public void justTest() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
