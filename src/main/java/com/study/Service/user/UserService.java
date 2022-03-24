package com.study.Service.user;

import com.study.pojo.User;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:34
 * @Description: 用户相关Service接口
 */
public interface UserService {
    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @return
     */
    User login(String username, String password);

    /**
     * 通过用户ID获取用户信息
     * @param uid   主键
     * @return
     */
    User getUserInfoById(Integer uid);

    /**
     * 更改用户信息
     * @param user  user对象
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 注册用户
     * @param username
     * @param password
     * @param email
     * @param screenName
     * @return
     */
    int userRegister(String username, String password, String email, String screenName);
}
