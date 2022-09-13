package com.woniu.spring.factory.support;

import com.sun.jmx.snmp.BerException;
import com.woniu.spring.exception.BeansException;
import com.woniu.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:37
 * @Description: 实例化Bean类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) {
        Object bean=null;
        //这里通过从beanDefinition拿到bean的class然后无参构造器实例化出bean对象并放到单例池中
        bean=createBeanInstance(beanDefinition,beanName,args);
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Class clazz=beanDefinition.getBeanClass();
        Constructor constructorToUse=null;//符合入参信息相对应的构造函数
        Constructor<?>[] declaredConstructors=clazz.getDeclaredConstructors();//bean所在类的全部构造函数
        for (Constructor ctor:declaredConstructors){

            // 判断这个构造函数的参数和传入的参数是否吻合
            // TODO 参数类型应该对比而不是仅仅比较长度
            if (null!=args && ctor.getParameterTypes().length == args.length){
                constructorToUse=ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse,args);
    }
}
