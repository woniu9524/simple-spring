package com.woniu.spring.beans.context;

public interface ApplicationEventPublisher {

    //发布通知
    void publishEvent(ApplicationEvent event);

}