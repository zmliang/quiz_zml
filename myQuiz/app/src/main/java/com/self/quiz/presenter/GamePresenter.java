package com.self.quiz.presenter;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.self.quiz.game.GameSocket;
import com.self.quiz.game.IGameStatus;
import com.self.quiz.modal.PkRequest;
import com.self.quiz.modal.Question;
import com.self.quiz.utils.IGameProtocol;
import com.self.quiz.utils.JsonUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class GamePresenter extends BasePresenter<IGameStatus> implements IGameProtocol{
    private static final String TAG = GamePresenter.class.getSimpleName();


    public void test(){
        IGameStatus   status = new IGameStatus() {
            @Override
            public void open(GameSocket socket) {
                Log.i(TAG,"socket Opened");
                Gson gson = new Gson();
                socket.send(gson.toJson(new PkRequest(PK_REQUEST,4,null)));
            }

            @Override
            public void message(String data) {
                JsonUtils.parseListObj(data,Question.class);

            }

            @Override
            public void closed() {
                Log.i(TAG,"socket closed");
            }

            @Override
            public void error() {
                Log.i(TAG,"socket error");
            }
        };

        GameSocket gameSocket = new GameSocket(status);
        gameSocket.connect();

        /*
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        final GameSocket[] gameSocket = new GameSocket[1];
        Observable.create(new Observable.OnSubscribe<String>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Log.i(TAG,"call...");
                    gameSocket[0] = new GameSocket(new IGameStatus() {
                        @Override
                        public void open() {
                            subscriber.onNext(SOCKET_OPEN);
                        }

                        @Override
                        public void message(String data) {
                            subscriber.onNext(data);
                        }

                        @Override
                        public void closed() {
                            subscriber.onNext(SOCKET_CLOSE);
                        }

                        @Override
                        public void error() {
                            subscriber.onNext(ERROR);
                        }
                    });
                gameSocket[0].send(PK_REQUEST);
                }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//观察者在主线程
                .subscribe(new Observer<String>() {
            @Override
            public void onNext(String value) {
                Log.d(TAG, "对事件:"+ value +"作出响应"  );
            }

            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应："+e.getMessage());
            }

        });
        */


    }

}












