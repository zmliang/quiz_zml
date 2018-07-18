package com.self.quiz.components;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.self.quiz.R;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/18
 * CopyRight:  JinkeGroup
 */

public class CustomDialog extends Dialog implements View.OnClickListener{

    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private String Content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;


    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.Content = content;
    }

    public CustomDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.listener = listener;
        this.Content = content;
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public CustomDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public CustomDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(false);
        initView();
    }


    private void initView(){
        contentTxt = findViewById(R.id.content);
        titleTxt = findViewById(R.id.title);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);

        contentTxt.setText(Content);
        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                if(listener != null){
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if(listener != null){
                    listener.onClick(this, true);
                }
                this.dismiss();
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}
