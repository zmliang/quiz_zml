package com.self.quiz.game;

import org.java_websocket.client.WebSocketClient;

import rx.Subscriber;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public abstract class GameSocketCallBack extends Subscriber<GameSocketInfo> {
    public abstract void onMessage(String message);
    public abstract void onClose();
    public abstract void onOpen(WebSocketClient client);
    public abstract void onReconnect();
    private boolean hasOpened = false;

    @Override
    public void onCompleted(){
        if (hasOpened)
            onClose();
        hasOpened = false;
    }
    @Override
    public void onError(Throwable e) {

    }
    @Override
    public void onNext(GameSocketInfo webSocketInfo){
        if (webSocketInfo.getMessage()!=null){
            onMessage(webSocketInfo.getMessage());
        }else if (webSocketInfo.isOnOpen()){
            hasOpened = true;
            onOpen(webSocketInfo.getWebSocketClient());
        }else if (webSocketInfo.isOnReconnect()){
            onReconnect();
        }
    }
}
