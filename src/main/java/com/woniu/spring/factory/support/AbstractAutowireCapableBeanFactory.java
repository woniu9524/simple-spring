package com.woniu.spring.factory.support;

import com.sun.jmx.snmp.BerException;
import com.woniu.spring.exception.BeansException;
import com.woniu.spring.factory.config.BeanDefinition;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:37
 * @Description: 实例化Bean类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean=null;
        //这里通过从beanDefinition拿到bean的class然后无参构造器实例化出bean对象并放到单例池中
        try {
            bean=beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e)  {
            throw new BeansException("实例化bean失败",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }
}
