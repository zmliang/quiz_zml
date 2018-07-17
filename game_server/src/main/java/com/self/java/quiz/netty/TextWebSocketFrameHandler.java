package com.self.java.quiz.netty;

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

@ChannelHandler.Sharable
@Controller
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger logger = LogManager.getLogger(TextWebSocketFrameHandler.class.getName());

    @Autowired
    IGameService gameService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel incoming = ctx.channel();
        logger.trace("msg:"+msg.text());
        logger.trace("localAddress = " + incoming.remoteAddress().toString() + "\n");
        String message = msg.text();
        incoming.writeAndFlush(new TextWebSocketFrame(message));
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
         //   gameService.quitGame(ctx);
        } catch (Exception e) {
            logger.error(e.toString());
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
         //   gameService.quitGame(ctx);
            ctx.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

}