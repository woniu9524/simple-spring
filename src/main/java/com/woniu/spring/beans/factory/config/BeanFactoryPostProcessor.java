package com.woniu.spring.beans.factory.config;

import com.woniu.spring.beans.factory.ConfigurableListableBeanFactory;
import com.woniu.spring.exception.BeansException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-19  14:18
 * @Description: BeanFactory后置处理器，允许自定义修改BeanDefinition
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
