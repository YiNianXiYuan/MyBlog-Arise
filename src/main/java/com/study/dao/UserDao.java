package com.study.dao;

import com.study.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:58
 * @Description: 用户dao层接口
 */
@Mapper
public interface UserDao {/**
 * 根据用户名密码获取用户信息
 * @param username  用户名
 * @param password  密码
 * @return
 */
User getUserInfoByCond(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户ID获取用户信息
     * @param uid
     * @return
     */
    User getUserInfoById(Integer uid);

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 注册
     * @param username
     * @param password
     * @param email
     * @param screenName
     * @return
     */
    int userRegister(@Param("username")String username, @Param("password")String password, @Param("email")String email, @Param("screenName")String screenName);

}
