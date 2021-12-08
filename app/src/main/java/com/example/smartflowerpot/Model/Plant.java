package com.example.smartflowerpot.Model;

public class Plant {
    private String eui;
    private String nickname;
    private String dob;

    public Plant(String dob, String nickname, String eui) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
    }

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "plantID='" + eui + '\'' +
                ", nickname='" + nickname + '\'' +
                ", dob=" + dob +
                '}';
    }
}
