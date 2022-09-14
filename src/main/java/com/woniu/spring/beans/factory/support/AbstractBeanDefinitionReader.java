package com.woniu.spring.beans.factory.support;

import com.woniu.spring.core.io.DefaultResourceLoader;
import com.woniu.spring.core.io.ResourceLoader;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-14  11:20
 * @Description: Bean定义抽象类实现
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
