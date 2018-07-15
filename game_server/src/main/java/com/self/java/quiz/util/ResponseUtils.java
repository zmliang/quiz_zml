package com.self.java.quiz.util;

import com.self.java.quiz.netty.NettyHelper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpVersion;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

public class ResponseUtils {
    private static final String JSON_TYPE="application/json";
    public static void respone(ChannelHandlerContext ctx,final String content){
        ByteBuf buffer= Unpooled.wrappedBuffer(content.getBytes());
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                ErrorCode.errorToHttpResponseStatus(ErrorCode.EC_OK),buffer);
        response.headers().set(CONTENT_TYPE,JSON_TYPE);
        NettyHelper.sendResponse(ctx,response);
    }
}
