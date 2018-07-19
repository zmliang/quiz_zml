package com.self.java.quiz.dao;

import com.self.java.quiz.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zml
 * \* Date: 2018/7/13
 * \* Time: 14:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface IUserDao {

    /**
     * 获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    User selectUserByUserID(@Param("userId")String userId)throws Exception;

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws Exception
     */
    int updateUser(User user)throws Exception;

    /**
     * 更新用户头像
     * @param avatarUrl
     * @return
     * @throws Exception
     */
    int updateUserAvatar(@Param("avatarUrl") String avatarUrl,@Param("userId")String userId)throws Exception;

    /**
     * 根据用户名和密码登陆
     * @param nickname
     * @param pwd
     * @return
     * @throws Exception
     */
    User selectUser(@Param("nickName") String nickname,@Param("pwd")String pwd)throws Exception;

    /**
     * 插入用户
     * @param user
     * @return
     * @throws Exception
     */
    int insertUser(User user)throws Exception;

}
