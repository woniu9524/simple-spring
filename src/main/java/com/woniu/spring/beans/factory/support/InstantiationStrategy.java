package com.woniu.spring.beans.factory.support;

import com.woniu.spring.core.io.DefaultResourceLoader;
import com.woniu.spring.core.io.ResourceLoader;
import com.woniu.spring.exception.BeansException;
import com.woniu.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

//实例化策略接口
public interface InstantiationStrategy {
    //在实例化接口 instantiate 方法中添加必要的入参信息
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;

}
