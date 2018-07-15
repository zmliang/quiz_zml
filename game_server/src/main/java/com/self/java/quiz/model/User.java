package com.self.java.quiz.model;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zml
 * \* Date: 2018/7/13
 * \* Time: 14:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class User implements Serializable{
    private String userId;
    private String nickName;
    private String avatarUrl;
    private String passWord;

    public User() {
    }

    public User(String userId, String nickName, String avatarUrl, String passWord) {
        this.userId = userId;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.passWord = passWord;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
