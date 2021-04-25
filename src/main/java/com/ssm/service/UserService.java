package com.ssm.service;

import com.ssm.bean.User;
import com.ssm.dao.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @user 郑超
 * @date 2021/4/25
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User userLogin(long mobileNo, String password) {
        return userMapper.getUser(mobileNo, password);
    }

    public User userUpload(User user) {
        int i = userMapper.updateUserById(user);
        if (i == 1) return user;
        else return null;
    }

    public User findUserByLoginToken(String loginToken) {
        return userMapper.getUserByLoginToken(loginToken);
    }

}
