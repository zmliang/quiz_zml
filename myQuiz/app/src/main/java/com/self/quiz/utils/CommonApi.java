package com.self.quiz.utils;

import com.self.quiz.modal.HttpResult;
import com.self.quiz.modal.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public interface CommonApi {

    String BASE_URL = "http://47.98.219.111:9002/";
  //  String BASE_URL = "http://127.0.0.1:8080/";

    @GET("user")
    Observable<HttpResult<User>> login(@Query("nickName")String nickName,@Query("password")String password);

    @POST("upload")
    Observable<HttpResult<String>> upload_avatar(@Body RequestBody imgs, @Query("fileName")String fileName,@Query("userId")String userId);

    @POST("update")
    Observable<HttpResult<String>> update(@Query("user")String user);

}














