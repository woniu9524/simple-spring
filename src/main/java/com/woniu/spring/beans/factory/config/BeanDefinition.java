package com.woniu.spring.beans.factory.config;

import com.woniu.spring.beans.PropertyValues;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:44
 * @Description: BeanDefinition，用于定义 Bean 实例化信息
 */
public class BeanDefinition {
    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues=new PropertyValues();
    }

    public BeanDefinition(Class beanClass,PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues=propertyValues==null?new PropertyValues():propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
