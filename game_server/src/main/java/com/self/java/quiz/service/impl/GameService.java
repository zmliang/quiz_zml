package com.self.java.quiz.service.impl;

import com.self.java.quiz.service.IGameService;
import com.self.java.quiz.model.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService implements IGameService {
    private static final Logger logger = LogManager.getLogger(GameService.class.getName());
    private static final String PK_RESPONSE_HEAD = "PkRequest";
    private static final String PK_RESPONSE_SUCCESS = "success";

}
