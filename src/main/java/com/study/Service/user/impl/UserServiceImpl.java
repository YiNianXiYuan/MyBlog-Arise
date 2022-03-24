package com.study.Service.user.impl;

import com.study.Service.user.UserService;
import com.study.constant.ErrorConstant;
import com.study.dao.UserDao;
import com.study.exception.BusinessException;
import com.study.pojo.User;
import com.study.utils.TaleUtils;
import com.study.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:33
 * @Description: 用户相关Service接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;//这里会报错，但是并不影响

    @Override
    public int userRegister(String username, String password, String email, String screenName) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        // 查询账号是否存在
        String pwd = TaleUtils.MD5encode(username + password);
        User user = userDao.getUserInfoByCond(username, pwd);
        int state = userDao.userRegister(username, pwd, email, screenName);
        if (null == user) {
            if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password) &&
                    !StringUtils.isBlank(email) && !StringUtils.isBlank(screenName)) {
                return 1;
            }
        }
        return state;
    }

    @Override
    public User login(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);

        String pwd = TaleUtils.MD5encode(username + password);
        User user = userDao.getUserInfoByCond(username, pwd);
        if (null == user)
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        return user;
    }

    @Override
    public User getUserInfoById(Integer uid) {
        return userDao.getUserInfoById(uid);
    }

    // 开启事务
    @Transactional
    @Override
    public int updateUserInfo(User user) {
        if (null == user.getUid())
            throw BusinessException.withErrorCode("用户编号不能为空");
        return userDao.updateUserInfo(user);
    }
}
