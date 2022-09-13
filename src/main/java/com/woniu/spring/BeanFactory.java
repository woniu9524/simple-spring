package com.woniu.spring;

import com.woniu.spring.exception.BeansException;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:45
 * @Description: BeanFactory，代表了 Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取。
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    //重载了一个含有入参信息 args，这样就可以方便的传递入参给构造函数实例化了。
    Object getBean(String beanName, Object... args) throws BeansException;
}
