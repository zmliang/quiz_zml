package com.self.quiz.view;

import com.self.quiz.modal.User;

/**
 * Created by zmliang on 2018/7/15.
 */

public interface ILoginView extends BaseView {
    String getUserName();
    String getPassWord();
    void onLoginResult(User user);

}
