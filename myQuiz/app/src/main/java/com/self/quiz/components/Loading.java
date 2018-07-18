package com.self.quiz.components;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.self.quiz.R;

/**
 * Created by zmliang on 2018/7/16.
 */
public class Loading extends ProgressDialog {
    private String mMessage;
    private TextView mTitleTv;


    public Loading(Context context, String message, boolean canceledOnTouchOutside) {
        super(context, R.style.Theme_Light_LoadingDialog);
        this.mMessage = message;
        setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        mTitleTv = findViewById(R.id.tv_loading_dialog);
        mTitleTv.setText(mMessage);
        setCancelable(false);
    }

    public void setTitle(String message) {
        this.mMessage = message;
        mTitleTv.setText(mMessage);
    }

}
