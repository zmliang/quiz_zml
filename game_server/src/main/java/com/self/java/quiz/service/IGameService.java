package com.self.java.quiz.service;

import com.self.java.quiz.model.Game;
import com.self.java.quiz.model.PkRequest;
import com.self.java.quiz.model.Question;
import io.netty.util.AttributeKey;

import java.util.List;
import java.util.Queue;

public interface IGameService {

    boolean isPkRequest(String msg);

    List<Question> onPrepareQuestion(final int qType) throws Exception;

    void onPkEnd(Game game);

    boolean isPkEnd(String msg);

    void onPkError(Game game);

    void onPkQuit(Game game);

}