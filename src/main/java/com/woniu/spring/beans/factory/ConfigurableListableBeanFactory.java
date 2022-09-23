package com.woniu.spring.beans.factory;

import com.woniu.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.config.BeanPostProcessor;
import com.woniu.spring.beans.factory.config.ConfigurableBeanFactory;
import com.woniu.spring.exception.BeansException;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}