package com.woniu.spring.beans.context;

import com.woniu.spring.beans.factory.HierarchicalBeanFactory;
import com.woniu.spring.beans.factory.ListableBeanFactory;
import com.woniu.spring.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
