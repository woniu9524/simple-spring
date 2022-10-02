package com.woniu.spring.beans.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.woniu.spring.beans.factory.config.BeanDefinition;
import com.woniu.spring.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-10-01  11:06
 * @Description: 处理对象扫描配置
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
