package com.woniu.spring.core.io;

import cn.hutool.core.lang.Assert;
import com.woniu.spring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-14  10:31
 * @Description: 用classLoad读取类路径下的资源
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        //这个this是调用下面那个方法的，一开始还没明白
        this(path, (ClassLoader) null);//不懂为啥对null还做转换
    }


    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }



    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if (resourceAsStream==null){
            throw new FileNotFoundException(this.path+"找不到这个文件");
        }
        return resourceAsStream;
    }
}
