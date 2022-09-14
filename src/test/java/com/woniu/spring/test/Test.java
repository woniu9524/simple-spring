package com.woniu.spring.test;

import cn.hutool.core.io.IoUtil;
import com.woniu.spring.beans.PropertyValue;
import com.woniu.spring.beans.PropertyValues;
import com.woniu.spring.UserDao;
import com.woniu.spring.UserService;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.config.BeanReference;
import com.woniu.spring.beans.factory.support.DefaultListableBeanFactory;
import com.woniu.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.woniu.spring.core.io.DefaultResourceLoader;
import com.woniu.spring.core.io.Resource;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;


/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @org.junit.Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/application.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService =beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
