package com.woniu.spring.beans.context.support;

import com.woniu.spring.beans.context.ApplicationEvent;
import com.woniu.spring.beans.context.ApplicationListener;
import com.woniu.spring.beans.context.ConfigurableApplicationContext;
import com.woniu.spring.beans.context.event.ApplicationEventMulticaster;
import com.woniu.spring.beans.context.event.ContextClosedEvent;
import com.woniu.spring.beans.context.event.ContextRefreshedEvent;
import com.woniu.spring.beans.context.event.SimpleApplicationEventMulticaster;
import com.woniu.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.woniu.spring.beans.factory.config.BeanPostProcessor;
import com.woniu.spring.beans.factory.ConfigurableListableBeanFactory;
import com.woniu.spring.core.io.DefaultResourceLoader;
import com.woniu.spring.exception.BeansException;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-19  14:12
 * @Description: ApplicationContext的抽象实现类
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;


    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();

        // 8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9. 发布容器刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 15:41
     * @param: [beanFactory]
     * @return: void
     * @description:调用Bean工厂的后置处理器
     * 先找到实现BeanFactoryPostProcessor的类，然后生成它们的bean并且调用其postProcessBeanFactory方法
     **/
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 15:45
     * @param: [beanFactory]
     * @return: void
     * @description:注册Bean的后置处理器
     * 把bean的后置处理器对象加入到bean工厂中，留着后面使用
     **/
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 14:46
     * @param: []
     * @return: void
     * @description: 初始化事件发布者
     * 注册一个实践广播器到bean工厂中
     **/
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 14:51
     * @param: []
     * @return: void
     * @description: 注册事件监听器
     **/
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 14:51
     * @param: []
     * @return: void
     * @description: 发布容器刷新完成事件
     **/
    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
