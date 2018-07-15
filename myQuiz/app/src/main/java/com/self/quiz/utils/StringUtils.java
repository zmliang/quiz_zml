package com.self.quiz.utils;

/**
 * Created by zmliang on 2018/7/15.
 */

public class StringUtils {
    public static boolean isNull(final String value){
        if (value == null || value.equals("")){
            return true;
        }
        return false;
    }
}
