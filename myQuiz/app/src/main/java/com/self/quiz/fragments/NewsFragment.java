package com.self.quiz.fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;

import com.self.quiz.R;
import com.self.quiz.adapter.NewsAdapter;
import com.self.quiz.modal.NewsItem;
import com.self.quiz.presenter.NewsPresenter;
import com.self.quiz.view.INewsView;

import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class NewsFragment extends BaseFragment implements INewsView,SwipeRefreshLayout.OnRefreshListener{
    private final static String TAG = NewsFragment.class.getSimpleName();
    private NewsPresenter newsPresenter = new NewsPresenter(this);
    private RecyclerView newsList;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
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
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setOnRefreshListener(this);
        newsList = view.findViewById(R.id.news_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        newsList.setLayoutManager(manager);
        newsList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        newsList.addOnScrollListener(new OnScrollListener() {
            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager1 = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastPos = manager1.findLastCompletelyVisibleItemPosition();
                    int totalCount = manager1.getItemCount();
                    Log.i(TAG,"lastPos:"+lastPos+"; total:"+totalCount);
                    if (lastPos == totalCount-1 && isSlidingToLast){
                        onToast("加载中...");
                        newsPresenter.getNews(++page);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    isSlidingToLast = true;
                }else {
                    isSlidingToLast = false;
                }
            }
        });
        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new NewsAdapter(getContext());
        newsList.setAdapter(adapter);
    }


    @Override
    public void fetchData(List<NewsItem> list) {
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
        if (page == 1){
            onToast("已刷新...");
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        page = 1;
        newsPresenter.getNews(page);
    }
}
