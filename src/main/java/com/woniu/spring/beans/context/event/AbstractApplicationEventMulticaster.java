package com.woniu.spring.beans.context.event;

import com.woniu.spring.beans.context.ApplicationEvent;
import com.woniu.spring.beans.context.ApplicationListener;
import com.woniu.spring.beans.factory.BeanFactory;
import com.woniu.spring.beans.factory.BeanFactoryAware;
import com.woniu.spring.exception.BeansException;
import com.woniu.spring.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-24  09:39
 * @Description: 事件的抽象类，所有的事件包括关闭、刷新，以及用户自己实现的事件，都需要继承这个类。
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    //应用监听器集合
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;


    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 10:47
     * @param: [event]
     * @return: java.util.Collection<com.woniu.spring.beans.context.support.ApplicationListener>
     * @description: 摘取符合广播事件中的监听处理器，具体过滤动作在 supportsEvent 方法中
     **/
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {

        LinkedList<ApplicationListener> allListeners = new LinkedList<ApplicationListener>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) allListeners.add(listener);
        }
        return allListeners;
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 10:24
     * @param: [applicationListener, event]
     * @return: boolean
     * @description: 监听器是否对该事件感兴趣
     * 主要包括对Cglib、Simple不同实例化需要获取目标Class，Cglib代理类需要获取父类的Class，普通实例化的不需要。
     * 接下来就是通过提取接口和对应的 ParameterizedType 和 eventClassName，
     * 方便最后确认是否为子类和父类的关系，以此证明此事件归这个符合的类处理。
     **/
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;

        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();

        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }

}

