package com.self.java.quiz.model;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable{

    private PersonalInfo my;
    private PersonalInfo opposite;

    public Game() {
    }

    public Game(PersonalInfo my, PersonalInfo opposite) {
        this.my = my;
        this.opposite = opposite;
    }

    public PersonalInfo getMy() {
        return my;
    }

    public void setMy(PersonalInfo my) {
        this.my = my;
    }

    public PersonalInfo getOpposite() {
        return opposite;
    }

    public void setOpposite(PersonalInfo opposite) {
        this.opposite = opposite;
    }

    @Override
    public String toString() {
        return "Game{" +
                "my=" + my +
                ", opposite=" + opposite +
                '}';
    }
}
