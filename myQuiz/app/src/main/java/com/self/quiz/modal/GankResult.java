package com.self.quiz.modal;

import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class GankResult {

    private boolean error;
    private List<GankItem> results;

    public GankResult() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankItem> getResults() {
        return results;
    }

    public void setResults(List<GankItem> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankResult{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
