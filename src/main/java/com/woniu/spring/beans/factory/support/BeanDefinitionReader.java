package com.woniu.spring.beans.factory.support;

import com.woniu.spring.core.io.Resource;
import com.woniu.spring.core.io.ResourceLoader;
import com.woniu.spring.exception.BeansException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-14  11:11
 * @Description: Bean定义读取接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
