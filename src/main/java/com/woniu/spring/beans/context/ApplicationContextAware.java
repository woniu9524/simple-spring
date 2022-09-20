package com.woniu.spring.beans.context;

import com.woniu.spring.beans.factory.xml.Aware;
import com.woniu.spring.exception.BeansException;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
