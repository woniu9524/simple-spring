package com.woniu.spring.test.event;


import com.woniu.spring.beans.context.event.ContextRefreshedEvent;
import com.woniu.spring.beans.context.ApplicationListener;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
