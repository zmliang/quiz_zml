package com.self.quiz.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.self.quiz.App;
import com.self.quiz.modal.HttpResult;
import com.self.quiz.modal.User;
import com.self.quiz.utils.CallBack;
import com.self.quiz.utils.StringUtils;
import com.self.quiz.view.ILoginView;

import okhttp3.RequestBody;

/**
 * Created by zmliang on 2018/7/15.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    private final static String TAG = LoginPresenter.class.getSimpleName();
    private Context mContext;
    private  ILoginView loginView;
    public LoginPresenter(ILoginView view){
        loginView = view;
        attachView(view);
        mContext = App.getInstance();
    }

    public void login(){
        final String name = loginView.getUserName();
        final String pwd = loginView.getPassWord();
        if(StringUtils.isNull(name) || StringUtils.isNull(pwd)){
            Log.e(TAG,"username or pwd is null");
        }
        loginView.onShowDialog();
        CallBack<HttpResult<User>> subscriber1=new CallBack<HttpResult<User>>() {
            @Override
            public void onSuccess(HttpResult<User> model) {
                Log.i(TAG,"http success:"+model.toString());
                loginView.onLoginResult(model.getData());
            }

            @Override
            public void onFailed(String message) {
                loginView.onLoginResult(null);
            }

            @Override
            public void onFinished() {
                loginView.onCancelDialog();
            }
        };
        addSubscription(mApi.login(name,pwd),subscriber1);
    }
}
