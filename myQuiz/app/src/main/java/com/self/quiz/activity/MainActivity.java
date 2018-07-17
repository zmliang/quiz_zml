package com.self.quiz.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.self.quiz.App;
import com.self.quiz.R;
import com.self.quiz.components.BaseActivity;
import com.self.quiz.utils.GlideCircleTransform;

/**
 * Created by zmliang on 2018/7/17.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        Glide.with(this).load(App.getInstance().getUser().getAvatarUrl())
                .centerCrop().
                transform(new GlideCircleTransform(this))
                .into((ImageView)findViewById(R.id.avatar));
    }


}
