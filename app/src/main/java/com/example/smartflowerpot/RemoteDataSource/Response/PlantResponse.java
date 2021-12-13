package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Plant;

public class PlantResponse {
    private String nickname;
    private String eui;
    private String dob;
    private int age;


    public Plant getPlant() {
        return new Plant(eui, nickname, dob, age);
    }
}
