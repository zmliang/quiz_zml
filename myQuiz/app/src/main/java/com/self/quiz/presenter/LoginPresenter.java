package com.self.quiz.presenter;

import android.util.Log;
import com.self.quiz.modal.HttpResult;
import com.self.quiz.modal.User;
import com.self.quiz.utils.CallBack;
import com.self.quiz.utils.IConstant;
import com.self.quiz.utils.SharedPreferencesUtils;
import com.self.quiz.utils.StringUtils;
import com.self.quiz.view.ILoginView;

/**
 * Created by zmliang on 2018/7/15.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements IConstant{
    private final static String TAG = LoginPresenter.class.getSimpleName();

    private  ILoginView loginView;
    public LoginPresenter(ILoginView view){
        loginView = view;
        attachView(view);
    }

    public void tryAutoLogin(){
        final boolean isFirst = firstLogin();
        loginView.firstLogin(isFirst);
        final boolean savePwd = savePwd();
        loginView.savePwd(savePwd);
        final boolean autoLogin = autoLogin();
        loginView.autoLogin(autoLogin);
        if (autoLogin){
            login();
        }
    }

    public String getLocalUserName(){
        return createSp().getString(USER_NAME);
    }

    public String getLocalUserPwd(){
        return createSp().getString(USER_PWD);
    }

    private boolean autoLogin(){
        return createSp().getBoolean(AUTO_LOGIN,false);
    }

    private boolean savePwd(){
        return createSp().getBoolean(SAVE_PWD,false);
    }

    private boolean firstLogin(){
        SharedPreferencesUtils utils = createSp();
        final boolean isFirst = utils.getBoolean(FIRST_KEY,true);
        if (isFirst){
            utils.putValues(new SharedPreferencesUtils.ContentValue(FIRST_KEY, false),
                    new SharedPreferencesUtils.ContentValue(SAVE_PWD, false),
                    new SharedPreferencesUtils.ContentValue(AUTO_LOGIN, false),
                    new SharedPreferencesUtils.ContentValue(USER_NAME, ""),
                    new SharedPreferencesUtils.ContentValue(USER_PWD, ""));
            return true;
        }
        return false;
    }

    private void saveUserLocal(final String name){
        createSp().putValues(new SharedPreferencesUtils.ContentValue(USER_NAME,name));
    }

    public void login(){
        final String name = loginView.getUserName();
        if(StringUtils.isNull(name)){
            loginView.onToast("你输入的账号为空");
            return;
        }
        final String pwd = loginView.getPassWord();
        if(StringUtils.isNull(pwd)){
            loginView.onToast("你输入的密码为空");
            return;
        }
        loginView.onShowDialog();
        saveUserLocal(name);
        CallBack<HttpResult<User>> subscriber1=new CallBack<HttpResult<User>>() {
            @Override
            public void onSuccess(HttpResult<User> model) {
                Log.i(TAG,"http success:"+model.toString());
                saveCheckBoxState();
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

    public SharedPreferencesUtils createSp(){
        return new SharedPreferencesUtils(QUIZ);
    }

    public void saveCheckBoxState(){
        if (loginView.autoLoginChecked()){
            createSp().putValues(new SharedPreferencesUtils.ContentValue(SAVE_PWD,true),
                    new SharedPreferencesUtils.ContentValue(AUTO_LOGIN,true),
                    new SharedPreferencesUtils.ContentValue(USER_PWD,loginView.getPassWord()));
        }else if (loginView.remeberPwdChecked()){
            createSp().putValues(new SharedPreferencesUtils.ContentValue(SAVE_PWD,false),
                    new SharedPreferencesUtils.ContentValue(AUTO_LOGIN,false),
                    new SharedPreferencesUtils.ContentValue(USER_PWD,""));
        }else if (!loginView.remeberPwdChecked()){
            createSp().putValues(new SharedPreferencesUtils.ContentValue(SAVE_PWD,true),
                    new SharedPreferencesUtils.ContentValue(AUTO_LOGIN,false),
                    new SharedPreferencesUtils.ContentValue(USER_PWD,loginView.getPassWord()));
        }

    }

    @Override
    public void detachView(){
        super.detachView();
        loginView = null;
    }
}
