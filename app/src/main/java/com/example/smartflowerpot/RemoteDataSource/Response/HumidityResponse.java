package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Humidity;

//  Ionut

public class HumidityResponse {
    private double relativeHumidity;
    private String timeStamp;

    public Humidity getHumidity(){
        return new Humidity(relativeHumidity, timeStamp);
    }


}
