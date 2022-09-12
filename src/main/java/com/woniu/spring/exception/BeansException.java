package com.woniu.spring.exception;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  00:30
 * @Description: 定义bean异常
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
