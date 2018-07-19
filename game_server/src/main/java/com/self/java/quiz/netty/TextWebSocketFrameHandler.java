package com.self.java.quiz.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.self.java.quiz.model.Game;
import com.self.java.quiz.model.PkRequest;
import com.self.java.quiz.model.Question;
import com.self.java.quiz.service.IGameProtocol;
import com.self.java.quiz.service.IGameService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@ChannelHandler.Sharable
@Controller
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements IGameProtocol{
    private static final Logger logger = LogManager.getLogger(TextWebSocketFrameHandler.class.getName());

    @Autowired
    IGameService gameService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel incoming = ctx.channel();
        logger.trace("msg:"+msg.text());
        logger.trace("localAddress = " + incoming.remoteAddress().toString() + "\n");
        String message = msg.text();
        if (gameService.isPkRequest(message)){
            incoming.writeAndFlush(new TextWebSocketFrame(PK_RESPONSE_SUCCESS));
            final PkRequest pkRequest = JSON.parseObject(message, PkRequest.class);
            final List<Question> questions = gameService.onPrepareQuestion(pkRequest.getqType());
            if (questions!=null){
                incoming.writeAndFlush(new TextWebSocketFrame(questions.toString()));
            }else {
                incoming.writeAndFlush(new TextWebSocketFrame(ERROR));
            }
        }else if (gameService.isPkEnd(message)){
            gameService.onPkEnd(incoming.attr(Game.GAME_ATTRIBUTE_KEY).get());
            incoming.attr(Game.GAME_ATTRIBUTE_KEY).remove();
            incoming.writeAndFlush(new TextWebSocketFrame(PK_END_CONFIRM));
        }else {
            incoming.writeAndFlush(new TextWebSocketFrame(UNKNOWN));
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        logger.trace("Client:" + incoming.remoteAddress().toString() + " join");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        logger.trace("Client:" + incoming.remoteAddress().toString() + " leave");
        try {
            gameService.onPkQuit(incoming.attr(Game.GAME_ATTRIBUTE_KEY).get());
        } catch (Exception e) {
            logger.error(e.toString());
        }finally {
            incoming.attr(Game.GAME_ATTRIBUTE_KEY).remove();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        logger.trace("Client:" + incoming.remoteAddress().toString() + " online");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        logger.trace("Client:" + incoming.remoteAddress().toString() + " offline");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        logger.error("Client:" + incoming.remoteAddress().toString() + ", Exception:" + cause.toString());
        try {
            gameService.onPkError(incoming.attr(Game.GAME_ATTRIBUTE_KEY).get());
            ctx.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }finally {
            incoming.attr(Game.GAME_ATTRIBUTE_KEY).remove();
        }
    }

}