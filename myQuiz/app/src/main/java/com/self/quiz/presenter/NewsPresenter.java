package com.self.quiz.presenter;

import android.util.Log;

import com.self.quiz.modal.NewsItem;
import com.self.quiz.utils.CallBack;
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
    private NewsCB callback;

    public NewsPresenter(INewsView view){
        newsView = view;
        attachView(view,0);
        callback = new NewsCB(newsView);
    }

    static class NewsCB extends CallBack<String> {
        private INewsView view;
        public NewsCB(INewsView view){
            this.view = view;
        }
        @Override
        public void onSuccess(String model) {
            Log.i(TAG,"RESULT:"+model);
            List<NewsItem> newsItems = HtmlParse.parseHtmlData(model);
            this.view.fetchData(newsItems);
        }

        @Override
        public void onFailed(String message) {
            Log.i(TAG,"Failed:"+message);
            this.view.onCancelDialog();
        }

        @Override
        public void onFinished() {
            this.view.onCancelDialog();
        }
    }

    public void getNews(int page){
        addSubscription(mApi.getNews("http://news.qq.com/c/816guonei_"+page +".htm?0.9464406841442563"),callback);
    }

}
