package com.woniu.spring.test.beans;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  15:55
 * @Description:
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}