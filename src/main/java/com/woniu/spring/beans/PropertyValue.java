package com.woniu.spring.beans;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  15:07
 * @Description: 属性值
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
