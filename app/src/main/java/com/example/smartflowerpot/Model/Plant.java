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
    //----------Ionut----------
    private String type;

    public Plant(String dob, String nickname, String eui, String type) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
        this.type = type;
    }
    //-------------------------

    @Ignore
    public Plant(@NonNull String eui, String nickname, String dob, int age) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
        this.age = age;
    }

    @NonNull
    public String getEui() {
        return eui;
    }

    public void setEui(@NonNull String eui) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "eui='" + eui + '\'' +
                ", nickname='" + nickname + '\'' +
                ", dob='" + dob + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }
}
