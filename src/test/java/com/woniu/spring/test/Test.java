package com.woniu.spring.test;

import cn.hutool.core.io.IoUtil;
import com.woniu.spring.beans.PropertyValue;
import com.woniu.spring.beans.PropertyValues;
import com.woniu.spring.UserDao;
import com.woniu.spring.UserService;
import com.woniu.spring.beans.context.support.ClassPathXmlApplicationContext;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.config.BeanReference;
import com.woniu.spring.beans.factory.support.DefaultListableBeanFactory;
import com.woniu.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.woniu.spring.core.io.DefaultResourceLoader;
import com.woniu.spring.core.io.Resource;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    @org.junit.Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }
}
