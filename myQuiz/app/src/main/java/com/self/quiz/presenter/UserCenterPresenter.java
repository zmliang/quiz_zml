package com.self.quiz.presenter;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.self.quiz.App;
import com.self.quiz.modal.HttpResult;
import com.self.quiz.modal.User;
import com.self.quiz.utils.CallBack;
import com.self.quiz.utils.StringUtils;
import com.self.quiz.utils.UriUtils;
import com.self.quiz.view.IUserCenterView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/18
 * CopyRight:  JinkeGroup
 */

public class UserCenterPresenter extends BasePresenter<IUserCenterView> {
    private final String TAG = UserCenterPresenter.class.getSimpleName();

    private IUserCenterView userCenterView;

    public UserCenterPresenter(IUserCenterView view){
        this.userCenterView = view;
        attachView(view);
    }

    public void onResetName(final String name){
        if (StringUtils.isNull(name)){
            userCenterView.onToast("输入的昵称不能为空");
            return;
        }
        User user = App.getInstance().getUser();
        user.setNickName(name);
        reset(user);
    }

    private void reset(final User user){
    //    userCenterView.onShowDialog();

        CallBack<HttpResult<User>> subscriber1=new CallBack<HttpResult<User>>() {
            @Override
            public void onSuccess(HttpResult<User> model) {
                Log.i(TAG,"http success:"+model.toString());
                userCenterView.reLogin();
            }

            @Override
            public void onFailed(String message) {
                Log.i(TAG,"onFailed:"+message);
                userCenterView.onToast("更新用户信息失败");
            }

            @Override
            public void onFinished() {
                userCenterView.onCancelDialog();
            }
        };

        String temp = null;
        try {
            temp = URLEncoder.encode(new Gson().toJson(user),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNull(temp)){
            userCenterView.onCancelDialog();
            userCenterView.onToast("参数编码错误");
            return;
        }
        addSubscription(mApi.update(temp),subscriber1);
    }


    public void onResetPwd(final String pwd){
        if (StringUtils.isNull(pwd)){
            userCenterView.onToast("密码不能为空");
            return;
        }
        User user = App.getInstance().getUser();
        user.setPassWord(pwd);
        reset(user);


    }


    public void onUpdateAvatar(Uri uri){
        Log.i(TAG,"Uri:"+uri.toString());
        File file = null;
        try {
            file = new File(UriUtils.getFilePathByUri(App.getInstance().getBaseContext(),uri));
            Log.i(TAG,"length:"+file.length());
        }catch (Exception E){
            E.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image",file.getName(),requestBody);
        CallBack<HttpResult<String>> callBack = new CallBack<HttpResult<String>>() {
            @Override
            public void onSuccess(HttpResult<String> model) {

            }

            @Override
            public void onFailed(String message) {

            }

            @Override
            public void onFinished() {

            }
        };
        addSubscription(mApi.upload_avatar(body),callBack);
    }

    @Override
    public void detachView(){
        super.detachView();
        userCenterView = null;
    }


}
