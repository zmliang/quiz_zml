package com.self.quiz.presenter;

import android.util.Log;

import com.self.quiz.game.GameSocket;
import com.self.quiz.game.GameSocketInfo;
import com.self.quiz.game.IGameStatus;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class GamePresenter extends BasePresenter<IGameStatus> {
    private static final String TAG = GamePresenter.class.getSimpleName();

    public void test(){
        Observable.create(new Observable.OnSubscribe<GameSocket>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void call(Subscriber<? super GameSocket> subscriber) {
                    subscriber.onNext(new GameSocket(null));
                }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//观察者在主线程
                .subscribe(new Observer<GameSocket>() {
            @Override
            public void onNext(GameSocket value) {
                Log.d(TAG, "对Next事件"+ value +"作出响应"  );
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

        });
    }

}












