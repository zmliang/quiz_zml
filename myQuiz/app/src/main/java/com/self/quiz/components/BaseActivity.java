package com.self.quiz.components;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.self.quiz.R;

/**
 * Created by zmliang on 2018/7/16.
 */

public abstract class BaseActivity extends AppCompatActivity{
    protected Loading loading ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView();
    }

    protected abstract int getLayoutID();
    public abstract void  initView();

    @Override
    public void startActivity(Intent intent){
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    protected void Toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish(){
        super.finish();
    }
}
