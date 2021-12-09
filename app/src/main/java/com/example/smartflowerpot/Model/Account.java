package com.example.smartflowerpot.Model;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String dob;
    private String gender;
    private String region;
    private ArrayList<Plant> plants;

    public Account() {
    }

    public Account(String username, String password, String dob, String gender, String region) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                ", plants=" + plants +
                '}';
    }
}
