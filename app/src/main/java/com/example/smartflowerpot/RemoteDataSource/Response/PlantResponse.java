package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Plant;

import java.sql.Timestamp;
import java.util.Date;

public class PlantResponse {
    private String nickname;
    private String eui;
    private String dob;
    private int age;

    public Plant getPlant() {
        return new Plant(dob, nickname, eui, age);
    }
}
