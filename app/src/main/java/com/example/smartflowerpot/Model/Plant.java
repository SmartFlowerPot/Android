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
    private String plantType;

    public Plant(String dob, String nickname, String eui, String plantType) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
        this.plantType = plantType;
    }
    //-------------------------

    @Ignore
    public Plant(@NonNull String eui, String nickname, String dob, int age, String plantType) {
        this.eui = eui;
        this.nickname = nickname;
        this.dob = dob;
        this.age = age;
        this.plantType = plantType;
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

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "eui='" + eui + '\'' +
                ", nickname='" + nickname + '\'' +
                ", dob='" + dob + '\'' +
                ", age=" + age +
                ", type='" + plantType + '\'' +
                '}';
    }
}
