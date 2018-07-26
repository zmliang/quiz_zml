package com.self.quiz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.self.quiz.R;
import com.self.quiz.components.BaseActivity;
import com.self.quiz.view.BaseView;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public abstract class BaseFragment extends Fragment implements BaseView{
    private final String TAG = BaseFragment.class.getSimpleName();

    public abstract int getLayoutID();
    public abstract void initView(View view);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onShowDialog() {
        ((BaseActivity)getContext()).onShowDialog("加载中");
    }

    @Override
    public void onCancelDialog() {
        ((BaseActivity)getContext()).onCancelDialog();
    }

    @Override
    public void onToast(String msg) {
        ((BaseActivity)getContext()).Toast(msg);
    }

}
