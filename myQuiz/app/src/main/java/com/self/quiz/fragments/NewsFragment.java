package com.self.quiz.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.self.quiz.R;
import com.self.quiz.adapter.NewsAdapter;
import com.self.quiz.modal.GankItem;
import com.self.quiz.modal.NewsItem;
import com.self.quiz.presenter.NewsPresenter;
import com.self.quiz.view.INewsView;

import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class NewsFragment extends BaseFragment implements INewsView {
    private NewsPresenter newsPresenter = new NewsPresenter(this);
    private RecyclerView newsList;
    NewsAdapter adapter;

    int page = 1;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        newsPresenter.getNews(page);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {
        newsList = view.findViewById(R.id.news_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        newsList.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new NewsAdapter(getContext());
        newsList.setAdapter(adapter);
    }


    @Override
    public void fetchData(List<NewsItem> list) {
        adapter.setDatas(list);
    }
}
