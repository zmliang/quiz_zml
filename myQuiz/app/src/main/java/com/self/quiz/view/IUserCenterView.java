package com.self.quiz.view;

import com.self.quiz.modal.User;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/18
 * CopyRight:  JinkeGroup
 */

public interface IUserCenterView extends BaseView {

    void updateUserInfor(final User user);
    void reLogin();

}
