package com.self.java.quiz;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDB {

    private  static SqlSessionFactory sqlSessionFactory;
    private static SqlSession session;

    public static void  beforeClass() throws IOException {
        // 1. 根据xml创建SqlSessionFactory对象

    }

    public void afterClass(){

    }


    public static void testLoad() throws IOException {

        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2. 获取session实例 能执行已经映射的sql语句
        session = sqlSessionFactory.openSession();
        // selectOne方法的两个参数：
        // sql的唯一标识
        // 执行sql要用的参数

        session.close();
    }
}
