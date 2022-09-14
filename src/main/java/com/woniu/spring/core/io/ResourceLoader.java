package com.woniu.spring.core.io;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-14  10:49
 * @Description: 资源加载器
 * 按照资源加载的不同方式，资源加载器可以把这些方式集中到统一的类服务下进行处理，外部用户只需要传递资源地址即可，简化使用。
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    //定义获取资源接口，里面传递 location 地址即可
    Resource getResource(String location);

}
