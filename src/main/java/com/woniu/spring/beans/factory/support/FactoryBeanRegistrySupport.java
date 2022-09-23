package com.woniu.spring.beans.factory.support;

import com.woniu.spring.beans.FactoryBean;
import com.woniu.spring.exception.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-21  10:27
 * @Description: FactoryBean 注册服务
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    /*
     * @author: zhangcheng
     * @date: 2022/9/21 19:54
     * @param: [beanName]
     * @return: java.lang.Object
     * @description:获取单例bean
     **/
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/21 19:55
     * @param: [factory, beanName]
     * @return: java.lang.Object
     * @description: 从bean工厂获取对象
     **/
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/21 19:56
     * @param: [factory, beanName]
     * @return: java.lang.Object
     * @description:具体获取bean方法
     **/
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }

}
