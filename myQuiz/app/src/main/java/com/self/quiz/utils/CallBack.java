package com.self.quiz.utils;

import android.util.Log;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by zmliang on 2018/7/15.
 */

public abstract class CallBack<T> extends Subscriber<T> {
    public abstract void onSuccess(T model);
    public abstract void onFailed(String message);
    public abstract void onFinished();

    @Override
    public void onError(Throwable e){
        e.printStackTrace();
        if (e instanceof HttpException){
            HttpException httpException = (HttpException)e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            onFailed(msg);
        }else {
            onFailed(e.getMessage());
        }
        onFinished();
    }

    @Override
    public void onCompleted(){
        onFinished();
    }
    @Override
    public void onNext(T model){
        Log.i("CallBack",model.toString());
        onSuccess(model);
    }
}
