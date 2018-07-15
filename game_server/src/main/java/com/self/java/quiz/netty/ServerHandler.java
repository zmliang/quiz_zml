package com.self.java.quiz.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;


@ChannelHandler.Sharable
@Controller
public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LogManager.getLogger(ServerHandler.class.getName());

    @Resource(name = "urlHandlerMap")
    private Map<String, Handler> urlHandlerMap;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request)
            throws Exception {
        logger.trace("MainServerHandler.channelRead, Uri: " + request.getUri());

        QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
        if (decoder.path() == null) {
            throw new DecoderException("path error");
        }
        if (decoder.path().startsWith("/ws")) {  //websocket
            ctx.fireChannelRead(request.retain());                  //2
        } else {
            try {
                //普通http请求
                int index = decoder.path().lastIndexOf("/");
                Handler handler = null;
                if (index != -1) {
                    String key = decoder.path().substring(index);
                    logger.debug("key = " + key);
                    handler = urlHandlerMap.get(key);
                }

                if (handler == null) {
                    throw new DecoderException("Not found handler for path: " + decoder.path());
                }
                handler.doHandler(ctx, decoder, request);

            } catch (DecoderException e) {
                logger.error("dispatcherService", e);
                NettyHelper.sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("dispatcherService", e);
                NettyHelper.sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.error("exceptionCaught", cause);
        NettyHelper.sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
}