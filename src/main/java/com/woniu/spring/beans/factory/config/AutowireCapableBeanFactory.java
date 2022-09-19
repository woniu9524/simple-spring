package com.woniu.spring.beans.factory.config;

import com.woniu.spring.beans.factory.xml.BeanFactory;
import com.woniu.spring.exception.BeansException;

/*
 * @author: zhangcheng
 * @date: 2022/9/19 17:14
 * @param:
 * @return:
 * @description:
 * 对于想要拥有自动装配能力，并且想把这种能力暴露给外部应用的BeanFactory类需要实现此接口。
 * 正常情况下，不要使用此接口，应该更倾向于使用BeanFactory或者ListableBeanFactory接口。
 * 此接口主要是针对框架之外，没有向Spring托管Bean的应用。
 * 通过暴露此功能，Spring框架之外的程序，具有自动装配等Spring的功能。
 *
 **/

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
