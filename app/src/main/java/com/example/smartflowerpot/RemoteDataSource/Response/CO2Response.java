package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.CO2;

public class CO2Response {
    private double co2;
    private String timeStamp;

    public CO2 getCO2(){
        return new CO2(co2, timeStamp);
    }
}
