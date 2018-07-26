package com.self.quiz.utils;

import android.os.Bundle;

import com.self.quiz.fragments.BaseFragment;
import com.self.quiz.fragments.PicsFragment;
import com.self.quiz.fragments.QuizFragment;
import com.self.quiz.fragments.NewsFragment;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class FragmentFactory {

    public static BaseFragment newInstance(String name){
        if (name.equals("视频")){
            NewsFragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            fragment.setArguments(bundle);
            return fragment;
        }else if (name.equals("图片")){
            return new PicsFragment();
        }else if (name.equals("答题")){
           return new QuizFragment();
        }
        return null;
    }

}
