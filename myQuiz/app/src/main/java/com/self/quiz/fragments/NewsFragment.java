package com.self.quiz.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.self.quiz.utils.CommonApi;
import com.self.quiz.view.INewsView;

import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class NewsFragment extends BaseFragment implements INewsView,SwipeRefreshLayout.OnRefreshListener
,NewsAdapter.OnNewsItemClickListener{
    private final static String TAG = NewsFragment.class.getSimpleName();
    private NewsPresenter newsPresenter = new NewsPresenter(this);
    private RecyclerView newsList;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private boolean loading = false;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        loading = true;
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
                        if (loading){
                            onToast("正在加载...");
                            return;
                        }
                        loading = true;
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
        adapter.setListener(this);
        newsList.setAdapter(adapter);
    }


    @Override
    public void fetchData(List<NewsItem> list) {
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
        loading = false;
    }

    @Override
    public void failed() {
        onToast("加载失败...");
        loading = false;
    }

    @Override
    public void finished() {
        loading = false;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        page = 1;
        newsPresenter.getNews(page);
    }

    @Override
    public void onNewsItemClicked(NewsItem news, View view) {
        Log.i(TAG,"点击了："+news.toString());
        Uri uri = Uri.parse(CommonApi.QQ_NEWS_BASE_URL+news.getNewsDetailUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
