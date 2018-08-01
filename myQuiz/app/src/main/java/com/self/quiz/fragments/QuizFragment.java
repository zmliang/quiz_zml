package com.self.quiz.fragments;

import android.content.Intent;
import android.view.View;

import com.self.quiz.R;
import com.self.quiz.activity.GameActivity;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class QuizFragment extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.quiz_fragment;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.start_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GameActivity.class));
            }
        });
    }
}
