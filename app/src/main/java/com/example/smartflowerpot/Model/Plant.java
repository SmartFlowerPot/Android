package com.example.smartflowerpot.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "plant_table")
public class Plant {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String eui;
    private String nickname;
    private String dob;
    private int age;

    public Plant(String dob, String nickname, String eui) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
    }

    @Ignore
    public Plant(@NonNull String eui, String nickname, String dob, int age) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
