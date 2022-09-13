package com.woniu.spring;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-12  23:49
 * @Description: UserService类
 */
public class UserService {
    private String name;

    public UserService() {
    }


    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}
