package com.assistant.service.user.impl;

import com.assistant.dao.user.UserMapper;
import com.assistant.entity.user.UserEntity;
import com.assistant.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/9/3.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户mapper
     */
    @Autowired
    UserMapper userDao;

    @Override
    public boolean verifyUserInformation(String username, String password) {
        return userDao.findUserInfo(username, password) == null;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
