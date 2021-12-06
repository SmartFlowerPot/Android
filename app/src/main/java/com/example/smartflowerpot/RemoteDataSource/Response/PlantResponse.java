package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Plant;

import java.util.Date;

public class PlantResponse {
    private String nickname;
    private String plantID;
    private Date date;

    public Plant getPlant() {
        return new Plant(plantID, nickname, date);
    }
}
