package com.woniu.spring;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:44
 * @Description: BeanDefinition，用于定义 Bean 实例化信息
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
