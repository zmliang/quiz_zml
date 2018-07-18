package com.self.quiz.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.self.quiz.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public class RetrofitClient {
    private static Retrofit mRetrofit;
    private static final int DEFAULT_TIMEOUT = 6;

    public static Retrofit retrofit(){
        if (mRetrofit == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG){
            //    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            //    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
             //   builder.addInterceptor(interceptor);
            }
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Content-Type","application/json")
                            .method(original.method(),original.body())
                            .build();
                    return chain.proceed(request);
                }
            });
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
            OkHttpClient okHttpClient = builder.build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(CommonApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }



    public static void close(){
        mRetrofit = null;
    }


}
