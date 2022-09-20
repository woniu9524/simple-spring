package com.woniu.spring.beans.factory.xml;

public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}