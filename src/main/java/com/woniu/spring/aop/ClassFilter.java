package com.woniu.spring.aop;

// 类匹配类，用于切点找到给定的接口和目标类
public interface ClassFilter {

    // 切点和目标类是否匹配
    boolean matches(Class<?> clazz);

}