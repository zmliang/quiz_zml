package com.self.java.quiz.netty;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.self.java.quiz.model.User;
import com.self.java.quiz.service.IUserService;
import com.self.java.quiz.util.ResponseUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLDecoder;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zml
 * \* Date: 2018/7/18
 * \* Time: 16:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class UpdateHandler implements Handler{
    private static final Logger logger = LogManager.getLogger(UpdateHandler.class.getName());
    @Autowired
    private IUserService userService;

    @Override
    public void doHandler(ChannelHandlerContext ctx, QueryStringDecoder decoder, FullHttpRequest request) throws Exception {
        logger.trace("updateHandler:"+request.toString());
        if (!decoder.parameters().containsKey("user")){
            throw new IllegalArgumentException("user infor is empty");
        }
        final String temp = URLDecoder.decode(decoder.parameters().get("user").get(0),"UTF-8");
        final User user = JSONObject.parseObject(temp,User.class);
        if (user==null){
            throw new JSONException("can't parse to User");
        }
        JSONObject result = new JSONObject();
        final boolean success = userService.updateUserInfor(user);
        if (!success){
            result.put("code",500);
            result.put("data",null);
            result.put("message","update failed");
        }else {
            result.put("code",200);
            result.put("data","success");
            result.put("message","update succeed");
        }
        ResponseUtils.respone(ctx,result.toJSONString());
    }
}
