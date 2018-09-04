package com.assistant.entity.test;

import java.io.Serializable;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
public class TestServiceEntity implements Serializable {

    private int uid;

    private String username;

    private String password;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
