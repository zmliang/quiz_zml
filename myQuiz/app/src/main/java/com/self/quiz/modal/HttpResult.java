package com.self.quiz.modal;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public class HttpResult<T> {
    private int code;
    private T data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", message=" + message +
                ", data=" + data +
                '}';
    }
}
