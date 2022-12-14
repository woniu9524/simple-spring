package com.woniu.spring.beans.factory;

import com.woniu.spring.exception.BeansException;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}