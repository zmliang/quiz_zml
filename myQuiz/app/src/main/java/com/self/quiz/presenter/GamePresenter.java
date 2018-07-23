package com.self.quiz.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.gson.Gson;
import com.self.quiz.game.GameSocket;
import com.self.quiz.game.IGameStatus;
import com.self.quiz.modal.PkRequest;
import com.self.quiz.modal.Question;
import com.self.quiz.utils.IGameProtocol;
import com.self.quiz.utils.JsonUtils;
import com.self.quiz.view.IGameView;
import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class GamePresenter  implements IGameProtocol{
    private static final String TAG = GamePresenter.class.getSimpleName();
    private final IGameView status;
    private GameSocket gameSocket = null;
    private myHandler mHandler  = null;


    private static void print(List<Question> questions){
        Log.i(TAG,"---------------------------------");
        for (Question question:questions){
            Log.i(TAG,question.toString());
        }
        Log.i(TAG,"------------------------------------");
    }


    public void readyNextQuestion(){
        Message msg = mHandler.obtainMessage(MSG_NEXT_QUESTION);
        mHandler.sendMessage(msg);
    }

    public GamePresenter(IGameView st){
        this.status = st;
        mHandler = new myHandler(st);
    }

    public void connect(final int qType){
        if (gameSocket!=null){
            status.onToast("socket已开启");
            return;
        }
        status.onShowDialog();
        final IGameStatus   listener = new IGameStatus() {
            @Override
            public void open(GameSocket socket) {
                Log.i(TAG,"socket Opened");
                Message msg = mHandler.obtainMessage(MSG_SOCKET_OPENED);
                mHandler.sendMessage(msg);
                Gson gson = new Gson();
                socket.send(gson.toJson(new PkRequest(PK_REQUEST,qType,null)));
            }

            @Override
            public void message(String data) {
                List<Question> questions = JsonUtils.parseListObj(data,Question.class);
                print(questions);
                Message msg = mHandler.obtainMessage(MSG_SOCKET_MESSAGE);
                msg.obj = questions;
                mHandler.sendMessage(msg);
            }

            @Override
            public void closed() {
                Log.i(TAG,"socket closed");
                Message msg = mHandler.obtainMessage(MSG_SOCKET_CLOSED);
                mHandler.sendMessage(msg);
                if (gameSocket!=null){
                    gameSocket = null;
                }
            }

            @Override
            public void error() {
                Log.i(TAG,"socket error");
                Message msg = mHandler.obtainMessage(MSG_SOCKET_ERROR);
                mHandler.sendMessage(msg);
            }
        };
        gameSocket = new GameSocket(listener);
        gameSocket.connect();
    }

    public void again(int qType){
    //    questions.clear();
        this.send(new Gson().toJson(new PkRequest(PK_REQUEST,qType,null)));
    }

    public void send(String message){
        gameSocket.send(message);
    }


    public void close(){
        if (gameSocket!=null){
            gameSocket.close();
        }
    }


    static class myHandler extends Handler{
        WeakReference<IGameView> gameView;
        List<Question> questions;
        public myHandler(IGameView view){
            this.gameView = new WeakReference<IGameView>(view);
        }

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            IGameView  gameView =this.gameView.get();
            switch (msg.what) {
                case MSG_SOCKET_OPENED:
                    gameView.opened();
                    break;
                case MSG_SOCKET_MESSAGE:
                    questions = (List<Question>) msg.obj;
                    gameView.next(questions.get(0), 1);
                    questions.remove(0);
                    print(questions);
                    break;
                case MSG_SOCKET_CLOSED:
                    gameView.closed();
                    break;
                case MSG_SOCKET_ERROR:
                    gameView.error();
                    //    status.onToast("连接出错");
                    break;
                case MSG_NEXT_QUESTION:
                    final int size = questions.size();
                    Log.i(TAG, "size" + size);
                    if (size <= 0) {
                        gameView.gameover();
                    } else {
                        Question q = questions.get(0);
                        gameView.next(q, 6 - size);
                        questions.remove(0);
                        print(questions);
                    }
                    break;
                default:
                    break;
            }
            gameView.onCancelDialog();
        }
    }

}












