package com.example.smartflowerpot.Repository;

public class TemperatureResponse {

    private static TemperatureResponse temperatureResponse;


    public static TemperatureResponse getInstance() {
        if (temperatureResponse == null)
            temperatureResponse = new TemperatureResponse();

    return temperatureResponse;
    }

    private TemperatureResponse() {
        temperatureResponse = TemperatureResponse.getInstance();
    }

    public static TemperatureResponse getTemperatureResponse() {
        return temperatureResponse;
    }
}
