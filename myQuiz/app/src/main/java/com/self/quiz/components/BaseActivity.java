package com.self.quiz.components;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zmliang on 2018/7/16.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initView();
    }

    public abstract void  initView();
}
