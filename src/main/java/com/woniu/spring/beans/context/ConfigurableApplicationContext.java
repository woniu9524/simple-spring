package com.woniu.spring.beans.context;

import com.woniu.spring.exception.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /*
     * @author: zhangcheng
     * @date: 2022/9/20 11:11
     * @param: []
     * @return: void
     * @description:注册虚拟钩子
     **/
    void registerShutdownHook();

    /*
     * @author: zhangcheng
     * @date: 2022/9/20 11:11
     * @param: []
     * @return: void
     * @description:手动关闭方法
     **/
    void close();
}
