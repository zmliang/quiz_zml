package com.self.quiz.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.self.quiz.App;
import com.self.quiz.R;
import com.self.quiz.utils.GlideCircleTransform;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/18
 * CopyRight:  JinkeGroup
 */

public class MenuHeader {
    public static View header(Context context, View.OnClickListener listener){
        View view = LayoutInflater.from(context).inflate(R.layout.main_menu_header,null);
        TextView username = view.findViewById(R.id.user_name);
        ImageView useravatar = view.findViewById(R.id.user_avatar);
        username.setText(App.getInstance().getUser().getNickName());

        useravatar.setOnClickListener(listener);

        Glide.with(context).load(App.getInstance().getUser().getAvatarUrl())
                .centerCrop().
                transform(new GlideCircleTransform(context))
                .into(useravatar);
        return view;
    }

}
