package com.woniu.spring.beans.factory.support;

import com.woniu.spring.beans.PropertyValues;
import com.woniu.spring.beans.factory.config.BeanPostProcessor;
import com.woniu.spring.exception.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    // 目标对象实例化之前调用，该方法的返回值类型是Object，我们可以返回任何类型的值
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    // 在 Bean 对象实例化完成后，设置属性操作之前执行此方法，对方法的属性值修改
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

}
