package com.self.java.quiz.dao;

import com.self.java.quiz.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zml
 * \* Date: 2018/7/19
 * \* Time: 18:09
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface IQuestionDao {

    List<Question> selectQuesRandom(@Param("limitCount")int limitCount)throws Exception;

    List<Question> selectQuesByType(@Param("type")int type,@Param("limitCount")int limitCount)throws Exception;

}
