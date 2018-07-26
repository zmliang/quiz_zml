package com.self.quiz.presenter;

import com.self.quiz.utils.CommonApi;
import com.self.quiz.utils.RetrofitClient;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zmliang on 2018/7/15.
 */

public class BasePresenter<T> {
    public T view;
    protected CommonApi mApi;

    public  enum  URL_TYPE{
        MY_URL,
        GANK_URL

    }

    private CompositeSubscription compositeSubscription;

    void attachView(T view,URL_TYPE urlType){
        this.view = view;
        mApi= RetrofitClient.retrofit().create(CommonApi.class);
    }

    void attachView(T view,int flag){
        this.attachView(view);
        mApi = RetrofitClient.newsRetrofit().create(CommonApi.class);
    }
    void attachView(T view){
        attachView(view,URL_TYPE.MY_URL);
    }

    public void detachView(){
        this.view = null;
        onUnsubscribe();
    }

     void onUnsubscribe(){
        if (compositeSubscription!=null &&
                compositeSubscription.hasSubscriptions()){
            compositeSubscription.clear();
            compositeSubscription.unsubscribe();
        }
    }

    void addSubscription(Observable observable, Subscriber subscriber){
        if (compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber));
    }

}
