package com.example.smartflowerpot.Model;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private ArrayList<Plant> plants;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, ArrayList<Plant> plants) {
        this.username = username;
        this.password = password;
        this.plants = plants;
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
                ", plants=" + plants +
                '}';
    }
}
