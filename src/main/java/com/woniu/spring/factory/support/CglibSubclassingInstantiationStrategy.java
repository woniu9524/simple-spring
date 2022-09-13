package com.woniu.spring.factory.support;

import com.woniu.spring.exception.BeansException;
import com.woniu.spring.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  10:38
 * @Description: Cglib实例化策略
 * TODO 要去学一下Cglib代理，这里有点不理解
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        //设置父类(将目标类作为代理类的父类)
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        //设置拦截器
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        //生成一个代理类对象并返回给调用着
        if (null == ctor) {
            return enhancer.create();
        }else {
            return enhancer.create(ctor.getParameterTypes(), args);
        }

    }
}
