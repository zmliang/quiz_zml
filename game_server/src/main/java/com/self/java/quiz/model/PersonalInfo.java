package com.self.java.quiz.model;

import org.springframework.beans.factory.annotation.Autowired;

import io.netty.channel.Channel;

import java.util.Timer;
import java.util.TimerTask;

public class PersonalInfo {

    public final static int MALE = 1;
    public final static int FEMALE = 0;

    Timer timer;
    private Channel channel;
    private String nickname;
    private String imageurl;
    private String userid;
    private int starnum;
    private int questype;
    private int gender;
    private String city;
    private int isRobot = 0;
    private int checkisowner;
    private String grade;//要挑战的等级
    private String myLevel = "";
    private int starLimit;

    public PersonalInfo(Channel channel, String nickname, String imageurl, String userid, int starnum, int questype, int gender, String city, int isRobot,String grade) {
        this.channel = channel;
        this.nickname = nickname;
        this.imageurl = imageurl;
        this.userid = userid;
        this.starnum = starnum;
        this.questype = questype;
        this.gender = gender;
        this.city = city;
        this.isRobot = isRobot;
        this.grade = grade;
    }

    public String getMyLevel() {
        return myLevel;
    }

    public void setMyLevel(String myLevel) {
        this.myLevel = myLevel;
    }

    public int getStarLimit() {
        return starLimit;
    }

    public void setStarLimit(int starLimit) {
        this.starLimit = starLimit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCheckisowner() {
        return checkisowner;
    }

    public void setCheckisowner(int checkisowner) {
        this.checkisowner = checkisowner;
    }

    public PersonalInfo(Channel channel, String nickname, String imageurl, String userid, int starnum, int questype, int isRobot,String grade) {
        this.channel = channel;
        this.nickname = nickname;
        this.imageurl = imageurl;
        this.userid = userid;
        this.starnum = starnum;
        this.questype = questype;
        this.gender = MALE;
        this.city = "未知";
        this.isRobot = isRobot;
        this.grade = grade;
    }

    public PersonalInfo(Channel channel, String nickname, String imageurl, String userid,
                        int starnum, int questype, int isRobot,String grade,String mylevel,int starlimit) {
        this.channel = channel;
        this.nickname = nickname;
        this.imageurl = imageurl;
        this.userid = userid;
        this.starnum = starnum;
        this.questype = questype;
        this.gender = MALE;
        this.city = "未知";
        this.isRobot = isRobot;
        this.grade = grade;
        this.myLevel = mylevel;
        this.starLimit = starlimit;
    }

    public PersonalInfo(Channel channel, String nickname, String imageurl, String userid, int starnum, int questype, int isRobot, int checkisowner) {
        this.channel = channel;
        this.nickname = nickname;
        this.imageurl = imageurl;
        this.userid = userid;
        this.starnum = starnum;
        this.questype = questype;
        this.gender = MALE;
        this.city = "未知";
        this.isRobot = isRobot;
        this.checkisowner = checkisowner;
    }

    public Timer getTimer() {

        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getQuestype() {
        return questype;
    }

    public void setQuestype(int questype) {
        this.questype = questype;
    }


    public PersonalInfo() {

    }

    public String getUserid() {

        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void cancelTimer() {
        timer.cancel();
    }

    public void startTimer(TimerTask timerTask) {
        timer = new Timer();

        timer.schedule(timerTask, 3000);
        //timer.cancel();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getStarnum() {
        return starnum;
    }

    public void setStarnum(int starnum) {
        this.starnum = starnum;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getIsRobot() {
        return isRobot;
    }

    public void setIsRobot(int isRobot) {
        this.isRobot = isRobot;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "nickname='" + nickname + '\'' +
                ", starnum=" + starnum +
                ", grade='" + grade + '\'' +
                ", myLevel='" + myLevel + '\'' +
                ", starLimit=" + starLimit +
                '}';
    }
}
