package com.self.java.quiz.model;

import io.netty.channel.Channel;
import java.io.Serializable;


public class PersonalInfo implements Serializable{

    private Channel channel;
    private String nickname;
    private String imageurl;
    private String userid;

    public PersonalInfo(Channel channel, String nickname, String imageurl, String userid) {
        this.channel = channel;
        this.nickname = nickname;
        this.imageurl = imageurl;
        this.userid = userid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "channel=" + channel +
                ", nickname='" + nickname + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
