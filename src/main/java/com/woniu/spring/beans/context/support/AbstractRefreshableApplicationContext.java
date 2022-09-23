package com.woniu.spring.beans.context.support;

import com.woniu.spring.beans.factory.support.DefaultListableBeanFactory;
import com.woniu.spring.beans.factory.ConfigurableListableBeanFactory;
import com.woniu.spring.exception.BeansException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-19  16:19
 * @Description: 获取Bean工厂和加载资源
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 16:42
     * @param: []
     * @return: void
     * @description:主要是获取了 DefaultListableBeanFactory 的实例化以及对资源配置的加载操作
     * loadBeanDefinitions(beanFactory)，在加载完成后即可完成对 spring.xml 配置文件中 Bean 对象的定义和注册，同时也包括实现了接口 BeanFactoryPostProcessor、BeanPostProcessor 的配置 Bean 信息。
     **/
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
