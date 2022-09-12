package com.woniu.spring.factory.support;

import com.woniu.spring.BeanFactory;
import com.woniu.spring.exception.BeansException;
import com.woniu.spring.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:48
 * @Description: Bean工厂的一个默认实现
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    //维护一张beanDefinitionMap表
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition==null){
            throw new BeansException("beanDefinition中没有找到"+beanName);
        }
        return beanDefinition;
    }

    //注册beanDefinition
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
