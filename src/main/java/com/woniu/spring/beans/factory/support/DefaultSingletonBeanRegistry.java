package com.woniu.spring.beans.factory.support;

import com.woniu.spring.beans.factory.config.SingletonBeanRegistry;
import com.woniu.spring.core.io.Resource;
import com.woniu.spring.core.io.ResourceLoader;
import com.woniu.spring.exception.BeansException;

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
