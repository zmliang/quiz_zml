package com.self.java.quiz.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class SocketHandler extends BaseHttpHandler {
    private static final Logger logger = LogManager.getLogger(SocketHandler.class.getName());
    private WebSocketServerHandshaker handshaker;
    private final String URI = "/ws";

    public void channelActive(ChannelHandlerContext ctx)throws Exception{
        logger.trace(ctx.channel().localAddress().toString()+" 通道已激活...");
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       logger.trace(ctx.channel().localAddress().toString() + " 通道不活跃！");
        // 关闭流
    }

    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.trace(msg.toString());
        if (msg instanceof FullHttpRequest) {// 如果是HTTP请求，进行HTTP操作
            logger.trace("into hettpHandle");
           // handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 如果是Websocket请求，则进行websocket操作
            logger.trace("into websockethandel");
          //  handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.trace("异常信息：\r\n" + cause.getMessage());
    }

}
