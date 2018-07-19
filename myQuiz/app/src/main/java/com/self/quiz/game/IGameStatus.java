package com.self.quiz.game;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public interface IGameStatus {
    void open();
    void message(String data);
    void closed();
    void error();
}
