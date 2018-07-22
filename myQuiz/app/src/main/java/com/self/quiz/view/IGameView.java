package com.self.quiz.view;

import com.self.quiz.modal.Question;

import java.util.List;
import java.util.Queue;

/**
 * Created by zmliang on 2018/7/22.
 */

public interface IGameView extends BaseView {
    void opened();
    void closed();
    void error();
    void next(Question question,int index);
    void gameover();

}
