package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Plant;

public class PlantResponse {
    private String nickname;
    private String eui;
    private String dob;
    private int age;
    private String plantType;


    public Plant getPlant() {
        return new Plant(eui, nickname, dob, age, plantType);
    }
}
