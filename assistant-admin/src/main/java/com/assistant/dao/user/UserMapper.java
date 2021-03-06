package com.assistant.dao.user;

import com.assistant.entity.user.UserEntity;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/9/3.
 */
public interface UserMapper {

    /**
     * 查询用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserEntity findUserInfo(String username, String password);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserEntity findByUsername(String username);
}
