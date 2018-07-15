package com.self.quiz.modal;

import java.io.Serializable;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public class User implements Serializable {
    private String userId;
    private String avatarUrl;
    private String nickName;
    private String passWord;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
