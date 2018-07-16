package com.self.quiz.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.self.quiz.App;

/**
 * Created by zmliang on 2018/7/16.
 */

public class SharedPreferencesUtils {
    private SharedPreferences sharedPreferences;
    public SharedPreferencesUtils(String fileName){
        sharedPreferences = App.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static class ContentValue{
        String key;
        Object value;
        public ContentValue(String key,Object value){
            this.key = key;
            this.value = value;
        }
    }

    public void putValues(ContentValue... contentValues){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (ContentValue contentValue:contentValues){
            if (contentValue.value instanceof String){
                editor.putString(contentValue.key,contentValue.value.toString()).commit();
            }
            if (contentValue.value instanceof Integer){
                editor.putInt(contentValue.key,Integer.parseInt(contentValue.value.toString())).commit();
            }
            if (contentValue.value instanceof Long) {
                editor.putLong(contentValue.key, Long.parseLong(contentValue.value.toString())).commit();
            }
            if (contentValue.value instanceof Boolean) {
                editor.putBoolean(contentValue.key, Boolean.parseBoolean(contentValue.value.toString())).commit();
            }

        }
    }

    public String getString(String key){
        return sharedPreferences.getString(key,null);
    }

    public boolean getBoolean(String key, Boolean b) {
        return sharedPreferences.getBoolean(key, b);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, -1);
    }
    public void clear(){
        sharedPreferences.edit().clear().commit();
    }


}











