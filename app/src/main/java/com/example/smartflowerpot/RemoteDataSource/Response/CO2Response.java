package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.CO2;

public class CO2Response {
    private int id;
    private double cO2Level;
    private String timeStamp;
    private String eui;

    public CO2 getCO2(){
        return new CO2(id,cO2Level, timeStamp, eui);
    }

}
