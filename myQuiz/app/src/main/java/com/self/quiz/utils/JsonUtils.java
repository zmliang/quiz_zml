package com.self.quiz.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/20
 * CopyRight:  JinkeGroup
 */

public class JsonUtils {

    public static  <T> List<T>  parseListObj(String resource,Class<T> cls){

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(resource).getAsJsonArray();
        List<T> list = new ArrayList<>();
        for (JsonElement element:array){
            T q = (T) gson.fromJson(element,cls);
            list.add(q);
        }
        return list;
    }

}
