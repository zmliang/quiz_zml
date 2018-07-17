package com.self.quiz.utils.websocket;

import android.util.Log;

import com.self.quiz.game.ISocketStatus;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class WSocketMgr extends WebSocketClient{
    private final static String TAG = WSocketMgr.class.getSimpleName();

    private ISocketStatus socketStatus;

    private WSocketMgr(URI serverURI) {
        super(serverURI);
    }

    public WSocketMgr( ISocketStatus status) {
        this(URI.create("ws://47.98.219.111:8800"));
        socketStatus = status;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(TAG,"onOpen:"+handshakedata.getHttpStatusMessage());
        socketStatus.open();
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG,"onMessage:"+message);
        socketStatus.message(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG,"onClose:"+reason);
        socketStatus.closed();
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG,"onError:"+ex.getMessage());
        socketStatus.error();
    }
}
