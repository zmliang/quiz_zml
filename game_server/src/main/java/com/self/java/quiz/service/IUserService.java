package com.self.java.quiz.service;

import com.self.java.quiz.model.User;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zml
 * \* Date: 2018/7/13
 * \* Time: 14:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface IUserService {

    User Login(final String userName,final String pwd) throws Exception;

    boolean updateUserInfor(final User user)throws Exception;

    boolean updateUserAvatar(final String url,final String userId)throws Exception;

    boolean register(User user)throws Exception;

}
