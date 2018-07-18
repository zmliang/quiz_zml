package com.self.java.quiz.netty;

import com.alibaba.fastjson.JSONObject;
import com.self.java.quiz.service.IUserService;
import com.self.java.quiz.util.ResponseUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadHandler implements Handler{
    private static final Logger logger = LogManager.getLogger(UploadHandler.class.getName());
    @Autowired
    private IUserService userService;

    @Override
    public void doHandler(ChannelHandlerContext ctx, QueryStringDecoder decoder, FullHttpRequest request) throws Exception {
        logger.trace(request.toString());
        ByteBuf buf = request.content();
        if (buf == null || !buf.isReadable()){
            throw new DecoderException("buf is not readable");
        }

        logger.trace("decoder:"+decoder.parameters().toString());


        File file = new File("/root/my.png");
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(0,bytes);
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bytes);
        fos.flush();
        fos.close();

        ResponseUtils.respone(ctx,new JSONObject().toJSONString());

    }
}












