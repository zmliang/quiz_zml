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
public class TextWebSocketFrameHandler extends
        SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger logger = LogManager.getLogger(TextWebSocketFrameHandler.class.getName());

    @Autowired
    IGameService gameService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                TextWebSocketFrame msg) throws Exception { // (1)
        Channel incoming = ctx.channel();
        logger.trace(msg.text());
        logger.trace("localAddress = " + incoming.remoteAddress().toString() + "\n");
        String message = msg.text();

        // 1. 根据'head'或关键字识别请求类型
        // 2. 判断请求参数正确性，需要确认的请求立即发送confirm
        // 3. 调用gameService层对应请求的接口进行处理，处理的结果需要回应的在gameService中以同步或异步方式发送

        // 4. 注意对gameService层的调用要进行异常捕获
        // 5. 在消息解析完成和发送响应到终端前，以debug打印接收和发送的消息内容
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

        return;
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