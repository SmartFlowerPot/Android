package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.LightLvl;

public class LightLvlResponse {
    private double lightLvl;
    private String timestamp;

    public LightLvl getLightLvl() {
        return new LightLvl(lightLvl, timestamp);
    }
}
