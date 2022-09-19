package com.woniu.spring.beans.context.support;

import com.woniu.spring.beans.factory.support.DefaultListableBeanFactory;
import com.woniu.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-19  16:43
 * @Description: 上下文中对配置信息的加载
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 17:04
     * @param: [beanFactory]
     * @return: void
     * @description:使用 XmlBeanDefinitionReader 类，处理了关于 XML 文件配置信息的操作
     **/
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /*
     * @author: zhangcheng
     * @date: 2022/9/19 17:03
     * @param: []
     * @return: java.lang.String[]
     * @description:从入口上下文类，拿到配置信息的地址描述
     **/
    protected abstract String[] getConfigLocations();

}
