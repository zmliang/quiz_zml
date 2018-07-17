package com.self.quiz.utils.websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;

import rx.Subscriber;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public abstract class WebSocketCB extends Subscriber<WebSocketInfo> {
    abstract void onMessage(String message);
    abstract void onClose();
    abstract void onOpen(WebSocketClient client);
    abstract void onReconnect();
    private boolean hasOpened = false;

    @Override
    public void onCompleted(){
        if (hasOpened)
            onClose();
        hasOpened = false;
    }
    @Override
    public void onNext(WebSocketInfo webSocketInfo){
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
