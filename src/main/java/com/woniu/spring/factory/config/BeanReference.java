package com.woniu.spring.factory.config;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  16:01
 * @Description: bean的类引用
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
