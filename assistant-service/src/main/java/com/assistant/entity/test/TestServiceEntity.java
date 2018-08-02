package com.assistant.entity.test;

import java.io.Serializable;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
public class TestServiceEntity implements Serializable {

    private int userId;

    private String userName;

    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
