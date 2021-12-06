package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Humidity;

//  Ionut

public class HumidityResponse {
    private double humidity;
    private String timestamp;

    public Humidity getHumidity(){
        return new Humidity(humidity, timestamp);
    }
    //TODO rename field variables here to match info from DB team
}
