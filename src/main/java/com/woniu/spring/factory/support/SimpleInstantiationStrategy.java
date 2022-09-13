package com.woniu.spring.factory.support;

import com.woniu.spring.exception.BeansException;
import com.woniu.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  10:40
 * @Description: JDK实例化策略
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz=beanDefinition.getBeanClass();
        try {
            if(null!=ctor){
                //有参构造
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
                //无参构造
                return clazz.getDeclaredConstructor().newInstance();
            }
        }
        catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new BeansException("实例化Bean失败");
        }
    }
}
