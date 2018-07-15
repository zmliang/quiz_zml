package com.self.java.quiz.netty;

import com.alibaba.fastjson.JSONObject;
import com.self.java.quiz.model.User;
import com.self.java.quiz.service.IUserService;
import com.self.java.quiz.util.ErrorCode;
import com.self.java.quiz.util.ResponseUtils;
import com.self.java.quiz.util.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

public class UserHandler implements Handler{
    private static final Logger logger = LogManager.getLogger(UserHandler.class.getName());


    @Autowired
    private IUserService userService;

    @Override
    public void doHandler(ChannelHandlerContext ctx, QueryStringDecoder decoder, FullHttpRequest request) throws Exception {
       logger.trace("userHandler:"+request.toString());
        if (!decoder.parameters().containsKey("nickName")) {
            throw new DecoderException("no nickName in query");
        }
        final String nickName = decoder.parameters().get("nickName").get(0);
        if (!decoder.parameters().containsKey("password")) {
            throw new DecoderException("no password in query");
        }
        final String passWord = decoder.parameters().get("password").get(0);
        if (StringUtil.isNull(passWord)){
            throw new InvalidParameterException("password is invalid");
        }
        if (StringUtil.isNull(nickName)){
            throw  new InvalidParameterException("nickName is invalid");
        }

        final User user =  userService.Login(nickName,passWord);
        JSONObject result = new JSONObject();
        if (user == null){
            result.put("code",500);
            result.put("data",null);
            result.put("message","login failed");
        }else {
            result.put("code",200);
            result.put("data",JSONObject.toJSON(user));
            result.put("message","login succeed");
        }
        ResponseUtils.respone(ctx,result.toJSONString());
    }

}
