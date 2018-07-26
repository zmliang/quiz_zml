package com.self.quiz.presenter;

import android.util.Log;

import com.self.quiz.modal.GankItem;
import com.self.quiz.modal.GankResult;
import com.self.quiz.modal.NewsItem;
import com.self.quiz.utils.CallBack;
import com.self.quiz.utils.CommonApi;
import com.self.quiz.utils.HtmlParse;
import com.self.quiz.view.INewsView;

import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class NewsPresenter extends BasePresenter<INewsView> {
    private static final String TAG = NewsPresenter.class.getSimpleName();

    private INewsView newsView;

    public NewsPresenter(INewsView view){
        newsView = view;
        attachView(view,0);
    }


    public void getNews(int page){

        CallBack<String> subscriber1=new CallBack<String>() {
            @Override
            public void onSuccess(String model) {
                Log.i(TAG,"http success:");
                List<NewsItem> newsItems = HtmlParse.parseHtmlData(model);
                newsView.fetchData(newsItems);
                Log.i(TAG,"--------------------");
                for (NewsItem item:newsItems){
                    Log.i(TAG,item.toString());
                }
                Log.i(TAG,"--------------------");
            }

            @Override
            public void onFailed(String message) {
                Log.i(TAG,"Failed:"+message);
                newsView.onCancelDialog();
            }

            @Override
            public void onFinished() {
                newsView.onCancelDialog();
            }
        };
        addSubscription(mApi.getNews("http://news.qq.com/c/816guonei_"+page +".htm?0.9464406841442563"),subscriber1);
    }

}
