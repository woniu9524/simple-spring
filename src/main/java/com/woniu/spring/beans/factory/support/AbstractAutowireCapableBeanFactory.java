package com.woniu.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.woniu.spring.beans.PropertyValue;
import com.woniu.spring.beans.PropertyValues;
import com.woniu.spring.beans.factory.*;
import com.woniu.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.beans.factory.config.BeanPostProcessor;
import com.woniu.spring.beans.factory.config.BeanReference;
import com.woniu.spring.exception.BeansException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:37
 * @Description: 实例化Bean类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) {
        Object bean=null;
        try {
            //这里通过从beanDefinition拿到bean的class然后无参构造器实例化出bean对象
            bean=createBeanInstance(beanDefinition,beanName,args);

            //给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);

            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);

        }catch (Exception e){
            throw new BeansException("初始化bean失败:"+e.getMessage(),e);
        }

        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 判断 SCOPE_SINGLETON、SCOPE_PROTOTYPE
        if (beanDefinition.isSingleton()) {
            //放到单例池中
            addSingleton(beanName, bean);
        }
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

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 17:15
     * @param: [beanName, bean, beanDefinition]
     * @return: java.lang.Object
     * @description:初始化bean方法
     **/
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }


        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        //TODO 此处原来的代码应该是漏了判断
        if (StrUtil.isNotEmpty(initMethodName)&&!(bean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 17:21
     * @param: [existingBean, beanName]
     * @return: java.lang.Object
     * @description:BeanPost的before方法
     * 拿到前面的bean后置处理器，执行postProcessBeforeInitialization方法
     * 如果返回对象，就替代原bean，否则返回正常的bean
     **/
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 17:23
     * @param: [existingBean, beanName]
     * @return: java.lang.Object
     * @description:BeanPost的after方法
     * 拿到前面的bean后置处理器，执行postProcessAfterInitialization方法
     * 如果返回对象，就替代原bean，否则返回正常的bean
     **/
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非 Singleton 类型的 Bean 不执行销毁方法
        if (!beanDefinition.isSingleton()) return;

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

}
