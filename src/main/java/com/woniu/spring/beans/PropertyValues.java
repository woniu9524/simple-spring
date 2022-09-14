package com.woniu.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-13  15:08
 * @Description: 属性集合
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList=new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }

    public PropertyValue[] getPropertyValues(){
        //toArray(T[] a)指定转化后的泛型
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
}
