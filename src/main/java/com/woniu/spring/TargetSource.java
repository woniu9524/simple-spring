package com.woniu.spring;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  15:12
 * @Description: 目标对象，在目标对象类中提供 Object 入参属性，以及获取目标类 TargetClass 信息
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }


    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }


    public Object getTarget(){
        return this.target;
    }

}