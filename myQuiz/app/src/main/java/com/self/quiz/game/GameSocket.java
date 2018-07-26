package com.self.quiz.game;

import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class GameSocket extends WebSocketClient {
    private final static String TAG = GameSocket.class.getSimpleName();

    private IGameStatus gameStatus;


    private GameSocket(URI serverURI) {
        super(serverURI);
    }

    public GameSocket(IGameStatus status) {
        this(URI.create("ws://47.98.219.111:9002/ws"));
        gameStatus = status;
    }


    @Override
    public void onOpen(ServerHandshake handshake) {
        Log.i(TAG,"onOpen:"+handshake.getHttpStatusMessage());
        if (gameStatus!=null)
            gameStatus.open(this);
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG,"onMessage:"+message);
        if (gameStatus!=null)
            gameStatus.message(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG,"onClose:"+reason);
        if (gameStatus!=null)
            gameStatus.closed();
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG,"onError:"+ex.getMessage());
        if (gameStatus!=null)
            gameStatus.error();
    }


}
