package com.woniu.spring.aop;

import com.woniu.spring.aop.framework.AopProxy;
import com.woniu.spring.aop.framework.Cglib2AopProxy;
import com.woniu.spring.aop.framework.JdkDynamicAopProxy;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  17:24
 * @Description: 代理工厂
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}