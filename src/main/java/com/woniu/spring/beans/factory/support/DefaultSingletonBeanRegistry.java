package com.woniu.spring.beans.factory.support;

import com.woniu.spring.beans.factory.config.SingletonBeanRegistry;
import com.woniu.spring.beans.factory.DisposableBean;
import com.woniu.spring.exception.BeansException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:13
 * @Description: 默认单例bean注册
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    //单例池
    private Map<String, Object> singletonObjects = new HashMap<>();
    //需要实现销毁方法的适配器对象
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    //获取单例bean
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    //添加单例bean
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
