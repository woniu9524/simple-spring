package com.woniu.spring.beans.context;

import java.util.EventObject;
/*
 * @author: zhangcheng
 * @date: 2022/9/24 9:05
 * @param:
 * @return:
 * @description: 具备事件功能的抽象类 ApplicationEvent
 **/
public abstract class ApplicationEvent extends EventObject {

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 9:03
     * @param: [source] The object on which the Event initially occurred.
     * @return:
     * @description:
     **/
    public ApplicationEvent(Object source) {
        super(source);
    }

}