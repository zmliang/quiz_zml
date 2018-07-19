package com.self.java.quiz.service.impl;

import com.self.java.quiz.dao.IQuestionDao;
import com.self.java.quiz.model.Question;
import com.self.java.quiz.service.IGameProtocol;
import com.self.java.quiz.service.IGameService;
import com.self.java.quiz.model.Game;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService implements IGameService ,IGameProtocol{
    private static final Logger logger = LogManager.getLogger(GameService.class.getName());

    @Autowired
    private IQuestionDao questionDao;

    @Override
    public boolean isPkRequest(String msg) {
        if (msg.contains(PK_REQUEST)){
            return true;
        }
        return false;
    }


    @Override
    public List<Question> onPrepareQuestion(int qType) throws Exception{
        return questionDao.selectQuesByType(qType,5);
    }

    @Override
    public void onPkEnd(Game thisgame) {

    }

    @Override
    public boolean isPkEnd(String msg) {
       if (msg.contains(IGameProtocol.PK_END))
           return true;
       return false;
    }

    @Override
    public void onPkError(Game thisgame) {

    }

    @Override
    public void onPkQuit(Game thisgame) {

    }

    private void cacheGame(Channel incoming,Game thisgame) {
        if (incoming.attr(Game.GAME_ATTRIBUTE_KEY).get() == null) {
            incoming.attr(Game.GAME_ATTRIBUTE_KEY).set(thisgame);
        } else {
            incoming.attr(Game.GAME_ATTRIBUTE_KEY).remove();
            incoming.attr(Game.GAME_ATTRIBUTE_KEY).set(thisgame);
        }
    }
}
