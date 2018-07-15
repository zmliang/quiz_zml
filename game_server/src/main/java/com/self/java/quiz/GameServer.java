package com.self.java.quiz;

import com.self.java.quiz.netty.ServerInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Server entry...
 */
public class GameServer {
    private static final Logger logger = LogManager.getLogger(GameServer.class.getName());

    public static ApplicationContext appCtx;

    public static void main(String[] args) {
        logger.trace("create applicatoin context for spring beans");
        appCtx = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        ServerInitializer server = (ServerInitializer) appCtx.getBean("serverInitializer");
        server.start();
    }
}
