package com.woniu.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-14  10:30
 * @Description: 资源加载接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
