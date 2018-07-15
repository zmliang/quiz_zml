package com.self.quiz;

import android.app.Application;

/*
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public class App extends Application {

    private static App instance = null;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        return instance;
    }

}
