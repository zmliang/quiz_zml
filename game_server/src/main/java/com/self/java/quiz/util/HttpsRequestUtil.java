package com.self.java.quiz.util;

import com.self.java.quiz.netty.MyX509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 公众平台通用接口工具类
 * 用户实现https请求
 *
 * @author zhangwenchao
 */
public class HttpsRequestUtil {
    private static final Logger logger = LogManager.getLogger(HttpsRequestUtil.class.getName());
    private static final String APPID = "wx1d768ab2ace5a2fe";
    private static final String SECRET = "8959ef5844d4295064a4815e35a9ad97";
    private static String WEIXIN_AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET;

    /**
     * 发起https请求并获取结果
     *
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static void httpRequest(String code, Callback callback) throws Exception {
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.setLength(0);
        urlBuffer.append(WEIXIN_AUTH_URL);
        urlBuffer.append("&js_code=");
        urlBuffer.append(code);
        urlBuffer.append("&grant_type=authorization_code");

        StringBuffer buffer = new StringBuffer();
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        HttpsURLConnection httpUrlConn = null;
        String str = null;
        try {
            sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            //打开连接
            URL url = new URL(urlBuffer.toString());
            logger.debug("url : " + urlBuffer.toString());
            httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            callback.sendMessage(buffer.toString());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                // 释放资源
                inputStream.close();
            }
            if (httpUrlConn != null) {
                httpUrlConn.disconnect();
            }
        }
    }

    public interface Callback {
        void sendMessage(String message) throws Exception;
    }
}
