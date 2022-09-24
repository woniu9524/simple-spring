package com.woniu.spring.beans.factory.config;

//单例注册接口定义
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
