package com.self.quiz.utils.websocket;

import org.java_websocket.client.WebSocketClient;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class WebSocketInfo {
    private WebSocketClient webSocketClient;
    private String message;
    private boolean onOpen;
    private boolean onReconnect;

    private WebSocketInfo(){

    }

    WebSocketInfo(WebSocketClient socket,boolean onOpen){
        webSocketClient = socket;
        this.onOpen = onOpen;
    }

    WebSocketInfo(WebSocketClient client,String msg){
        this.webSocketClient = client;
        this.message = msg;
    }

    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public void setWebSocketClient(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOnOpen() {
        return onOpen;
    }

    public void setOnOpen(boolean onOpen) {
        this.onOpen = onOpen;
    }

    public boolean isOnReconnect() {
        return onReconnect;
    }

    public void setOnReconnect(boolean onReconnect) {
        this.onReconnect = onReconnect;
    }
}
