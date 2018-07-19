package com.self.quiz.modal;

import java.io.Serializable;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/19
 * CopyRight:  JinkeGroup
 */

public class WSProtocol<T> implements Serializable{

    private String head;
    private T body;
    private final String version = "1.0";

    public WSProtocol(){

    }

    public WSProtocol(String head, T body) {
        this.head = head;
        this.body = body;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getVersion() {
        return version;
    }


}
