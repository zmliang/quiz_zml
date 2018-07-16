package com.self.quiz.view;

import com.self.quiz.modal.User;

/**
 * Created by zmliang on 2018/7/15.
 */

public interface ILoginView extends BaseView {
    String getUserName();
    String getPassWord();
    void onLoginResult(User user);
    void firstLogin(boolean isFirst);
    void savePwd(boolean isSaved);
    void autoLogin(boolean isAuto);

    boolean autoLoginChecked();
    boolean remeberPwdChecked();

}
