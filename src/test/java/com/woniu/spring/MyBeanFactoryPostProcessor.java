package com.woniu.spring;

import com.woniu.spring.beans.PropertyValue;
import com.woniu.spring.beans.PropertyValues;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.woniu.spring.beans.factory.xml.ConfigurableListableBeanFactory;
import com.woniu.spring.exception.BeansException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-19  17:24
 * @Description:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory执行……");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
