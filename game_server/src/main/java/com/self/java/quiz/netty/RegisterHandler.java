package com.self.java.quiz.netty;

import com.alibaba.fastjson.JSONObject;
import com.self.java.quiz.model.User;
import com.self.java.quiz.service.IUserService;
import com.self.java.quiz.util.ResponseUtils;
import com.self.java.quiz.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;
import java.util.UUID;

public class RegisterHandler implements Handler{
    private static final Logger logger = LogManager.getLogger(RegisterHandler.class.getName());

    @Autowired
    private IUserService userService;

    @Override
    public void doHandler(ChannelHandlerContext ctx, QueryStringDecoder decoder, FullHttpRequest request) throws Exception {
        logger.trace("registerHandler:"+request.toString());
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
        final User user = new User(UUID.randomUUID().toString(),nickName,"https://quiz.jktom.com/Abel.png",passWord);
        boolean result = userService.register(user);
        JSONObject json = new JSONObject();
        json.put("result",result);
        if (result){
            json.put("data",true);
            json.put("code",200);
            json.put("message","register succeed");
        }else {
            json.put("data",false);
            json.put("code",500);
            json.put("message","register occur error");
        }
        ResponseUtils.respone(ctx,json.toJSONString());
    }


}
