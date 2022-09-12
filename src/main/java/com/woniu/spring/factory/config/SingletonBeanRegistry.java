package com.woniu.spring.factory.config;

//单例注册接口定义
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
