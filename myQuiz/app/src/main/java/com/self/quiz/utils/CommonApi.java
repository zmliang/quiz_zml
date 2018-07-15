package com.self.quiz.utils;

import com.self.quiz.modal.HttpResult;
import com.self.quiz.modal.User;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public interface CommonApi {

    String BASE_URL = "http://47.98.219.111:9002/";

    @GET("user")
    Observable<HttpResult<User>> login(@Query("nickName")String nickName,@Query("password")String password);

}
