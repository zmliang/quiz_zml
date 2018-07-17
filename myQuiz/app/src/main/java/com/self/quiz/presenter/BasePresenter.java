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
    private CompositeSubscription compositeSubscription;

    public void attachView(T view){
        this.view = view;
        mApi= RetrofitClient.retrofit().create(CommonApi.class);
    }

    public void detachView(){
        this.view = null;
        onUnsubscribe();
    }

    public void onUnsubscribe(){
        if (compositeSubscription!=null &&
                compositeSubscription.hasSubscriptions()){
            compositeSubscription.clear();
            compositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber){
        if (compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber));
    }

}
