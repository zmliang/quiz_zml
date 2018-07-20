package com.self.quiz.activity;

import com.self.quiz.components.BaseActivity;
import com.self.quiz.presenter.GamePresenter;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class GameActivity extends BaseActivity {
    @Override
    public void initView() {
        new GamePresenter().test();
    }

}
