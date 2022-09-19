package com.woniu.spring.beans.factory.support;

import com.woniu.spring.beans.factory.config.BeanPostProcessor;
import com.woniu.spring.beans.factory.config.ConfigurableBeanFactory;
import com.woniu.spring.exception.BeansException;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.xml.BeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:17
 * @Description: 抽象bean工厂
 *
 * AbstractBeanFactory 首先继承了 DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法。
 * 接下来很重要的一点是关于接口 BeanFactory 的实现，在方法 getBean 的实现过程中可以看到，主要是对单例 Bean 对象的获取以及在获取不到时需要拿到 Bean 的定义做相应 Bean 实例化操作。那么 getBean 并没有自身的去实现这些方法，而是只定义了调用过程以及提供了抽象方法，由实现此抽象类的其他类做相应实现。
 * 后续继承抽象类 AbstractBeanFactory 的类有两个，包括：AbstractAutowireCapableBeanFactory、DefaultListableBeanFactory
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName,args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args){
        //从单例池中获取bean
        Object bean=getSingleton(beanName);
        if (bean!=null){
            return (T)bean;
        }
        //无法从单例池中获取时创建bean,此处体现了设计模式中的模板方法
        BeanDefinition beanDefinition=getBeanDefinition(beanName);
        return (T)createBean(beanName,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 15:53
     * @param: [beanPostProcessor]
     * @return: void
     * @description:添加bean的后置处理器
     **/
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }


    /*
     * @author: zhangcheng
     * @date: 2022/9/19 15:53
     * @param: []
     * @return: java.util.List<com.woniu.spring.beans.factory.config.BeanPostProcessor>
     * @description:获取bean的后置处理器
     **/
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}
