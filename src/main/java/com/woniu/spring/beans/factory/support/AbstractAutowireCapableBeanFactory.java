package com.woniu.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.woniu.spring.beans.PropertyValue;
import com.woniu.spring.beans.PropertyValues;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.config.BeanReference;

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
        //这里通过从beanDefinition拿到bean的class然后无参构造器实例化出bean对象
        bean=createBeanInstance(beanDefinition,beanName,args);

        //给 Bean 填充属性
        applyPropertyValues(beanName, bean, beanDefinition);

        //放到单例池中
        addSingleton(beanName,bean);
        return bean;
    }

    //创建bean实例
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

    //对象填充属性
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name=propertyValue.getName();
            Object value = propertyValue.getValue();

            //引用类型
            if (value instanceof BeanReference){
                // A 依赖 B，获取 B 的实例化
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
