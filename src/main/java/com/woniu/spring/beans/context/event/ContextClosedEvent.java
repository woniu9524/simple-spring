package com.woniu.spring.beans.context.event;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-24  09:31
 * @Description: 上下文关闭事件
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

}