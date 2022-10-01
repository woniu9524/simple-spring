package com.woniu.spring.aop;

import org.aopalliance.aop.Advice;

// 定义advisor访问者

public interface Advisor {
    Advice getAdvice();
}
