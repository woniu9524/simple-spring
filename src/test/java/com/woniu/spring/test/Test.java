//xxx
package com.woniu.spring.test;


import com.woniu.spring.beans.context.support.ClassPathXmlApplicationContext;
import com.woniu.spring.test.beans.UserService;
import com.woniu.spring.test.event.CustomEvent;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: 测试类
 */

public class Test {
    @org.junit.Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
