package com.example.smartflowerpot.Model;

import java.util.Date;

public class Plant {
    private String plantID;
    private String nickname;
    private Date date;

    public Plant(String plantID, String nickname, Date date) {
        this.plantID = plantID;
        this.nickname = nickname;
        this.date = date;
    }

    public String getPlantID() {
        return plantID;
    }

    public void setPlantID(String plantID) {
        this.plantID = plantID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "plantID='" + plantID + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + date +
                '}';
    }
}
