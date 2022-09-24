package com.woniu.spring.test.event;


import com.woniu.spring.beans.context.event.ContextClosedEvent;
import com.woniu.spring.beans.context.ApplicationListener;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
