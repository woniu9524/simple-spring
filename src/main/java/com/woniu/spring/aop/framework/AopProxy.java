package com.woniu.spring.aop.framework;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  15:24
 * @Description: 代理抽象实现
 */
public interface AopProxy {

    // 定义一个标准接口，用于获取代理类
    Object getProxy();

}
