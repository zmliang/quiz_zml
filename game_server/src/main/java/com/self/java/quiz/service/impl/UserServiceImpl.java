package com.self.java.quiz.service.impl;

import com.self.java.quiz.dao.IUserDao;
import com.self.java.quiz.model.User;
import com.self.java.quiz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zml
 * \* Date: 2018/7/13
 * \* Time: 14:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User Login(String userName, String pwd) throws Exception {
        return userDao.selectUser(userName,pwd);
    }

    @Override
    public boolean updateUserInfor(User user) throws Exception {
        return false;
    }

    @Override
    public boolean updateUserAvatar(String url) throws Exception {
        return false;
    }

    @Override
    public boolean register(User user) throws Exception {
        return userDao.insertUser(user)>0;
    }
}
