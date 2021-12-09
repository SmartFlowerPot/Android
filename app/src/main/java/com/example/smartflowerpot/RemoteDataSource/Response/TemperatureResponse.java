package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Temperature;

public class TemperatureResponse {

    private double temperatureInDegrees;
    private String timeStamp;


    public Temperature getTemperature() {
        return new Temperature(timeStamp, temperatureInDegrees);
    }
}
