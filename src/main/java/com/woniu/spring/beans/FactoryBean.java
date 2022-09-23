package com.woniu.spring.beans;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-21  10:26
 * @Description:
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}