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

    public void onShowDialog(final String msg){
        if (loading == null){
            loading = new Loading(this,msg,false);
        }
        if (!isFinishing())
            loading.show();
    }

    public void onShowDialog(int resID){
        onShowDialog(getString(resID));
    }

    public void onShowDialog(){
        onShowDialog(getString(R.string.login_ing));
    }

    public void onCancelDialog(){
        if (loading!=null){
            if (loading.isShowing()){
                loading.dismiss();
                loading.hide();
            }
        }
    }

    public void Toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    public void Toast(int resID){
        Toast(getString(resID));
    }

    @Override
    public void finish(){
        super.finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        onCancelDialog();
        loading = null;
    }
}
