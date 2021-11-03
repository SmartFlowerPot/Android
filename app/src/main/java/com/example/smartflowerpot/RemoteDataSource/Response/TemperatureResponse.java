package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;

import java.sql.Time;
import java.sql.Timestamp;

public class TemperatureResponse {

    private double temperature;
    private Timestamp timestamp;


    public Temperature getTemperature(){
        return new Temperature(temperature, timestamp);
    }
}
