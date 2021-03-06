package com.self.quiz.modal;

import java.io.Serializable;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/20
 * CopyRight:  JinkeGroup
 */

public class PkRequest implements Serializable {

    private String head;
    private int qType;
    private Object reserved;

    public PkRequest() {
    }

    public PkRequest(String head, int qType, Object reserved) {
        this.head = head;
        this.qType = qType;
        this.reserved = reserved;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getqType() {
        return qType;
    }

    public void setqType(int qType) {
        this.qType = qType;
    }

    public Object getReserved() {
        return reserved;
    }

    public void setReserved(Object reserved) {
        this.reserved = reserved;
    }
}
