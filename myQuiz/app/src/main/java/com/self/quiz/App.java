package com.self.quiz;

import android.app.Application;

import com.self.quiz.modal.User;

/*
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public class App extends Application {

    private static App instance = null;
    private User user;
    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        return instance;
    }

}
