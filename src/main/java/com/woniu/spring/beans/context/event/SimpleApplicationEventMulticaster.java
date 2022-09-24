package com.woniu.spring.beans.context.event;

import com.woniu.spring.beans.context.ApplicationEvent;
import com.woniu.spring.beans.context.ApplicationListener;
import com.woniu.spring.beans.factory.BeanFactory;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-24  11:23
 * @Description: 简单的事件广播器
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}

