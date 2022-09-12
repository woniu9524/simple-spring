package com.woniu.spring.factory.support;

import com.woniu.spring.factory.config.BeanDefinition;
//注册beanDefinition
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
