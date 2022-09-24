package com.woniu.spring.beans.context.event;

import com.woniu.spring.beans.context.ApplicationEvent;
import com.woniu.spring.beans.context.ApplicationListener;

// 事件广播器
public interface ApplicationEventMulticaster {

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 10:44
     * @param: [listener]
     * @return: void
     * @description: 添加监听器
     **/
    void addApplicationListener(ApplicationListener<?> listener);

    /*
     * @author: zhangcheng
     * @date: 2022/9/24 10:44
     * @param: [listener]
     * @return: void
     * @description: 移除监听器
     **/
    void removeApplicationListener(ApplicationListener<?> listener);


    /*
     * @author: zhangcheng
     * @date: 2022/9/24 10:45
     * @param: [event]
     * @return: void
     * @description: 广播事件
     **/
    void multicastEvent(ApplicationEvent event);

}