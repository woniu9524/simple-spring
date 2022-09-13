package com.woniu.spring;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: UserService类
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    // ...get/set
}
