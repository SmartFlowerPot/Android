package com.example.smartflowerpot.Repository;

import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;

public class TemperatureResponse {

    private ServiceResponse serviceResponse;
    private static TemperatureResponse temperatureResponse;


    private TemperatureResponse() {
        serviceResponse = ServiceResponse.getInstance();
    }

    public static TemperatureResponse getInstance() {
        if (temperatureResponse == null)
            temperatureResponse = new TemperatureResponse();

    return temperatureResponse;
    }

    public static TemperatureResponse getTemperatureResponse() {
        return temperatureResponse;
    }
}
