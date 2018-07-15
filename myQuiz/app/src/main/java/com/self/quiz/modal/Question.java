package com.self.quiz.modal;

import java.io.Serializable;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/13
 * CopyRight:  JinkeGroup
 */

public class Question implements Serializable {

    private String QuestionContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

    public Question(){

    }
    public Question(String content, String a, String b, String c, String d, String ar){
        this.QuestionContent = content;
        this.optionA = a;
        this.optionB = b;
        this.optionC = c;
        this.optionD = d;
        this.answer = ar;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "QuestionContent='" + QuestionContent + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
