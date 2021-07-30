package com.study.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午10:47
 * @Description: 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors//开启链式调用

public class User {
    //主键编号
    private Integer uid;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //主页地址
    private String homeUrl;
    //用户显示的名称
    private String screenName;
    //用户注册时的GMT Unix时间戳
    private Integer created;
    //最后活动时间
    private Integer activated;
    //上次登录最后活跃时间
    private Integer logged;
    //用户组
    private String groupName;
}
