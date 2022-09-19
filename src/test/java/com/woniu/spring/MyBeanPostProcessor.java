package com.woniu.spring;

import com.woniu.spring.beans.factory.config.BeanPostProcessor;
import com.woniu.spring.exception.BeansException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-19  17:26
 * @Description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBefore……");
        return null;
    }

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfter……");
        return null;
    }
}
