package com.woniu.spring.factory.support;

import com.woniu.spring.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:13
 * @Description: 默认单例bean注册
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    //单例池
    private Map<String, Object> singletonObjects = new HashMap<>();

    //获取单例bean
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    //添加单例bean
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
