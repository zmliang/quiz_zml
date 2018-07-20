package com.self.quiz.game;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public interface IGameStatus {
    void open(GameSocket socket);
    void message(String data);
    void closed();
    void error();
}
