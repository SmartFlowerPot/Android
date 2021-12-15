package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.LightLvl;

public class LightLvlResponse {
    private double lightLevel;
    private String timestamp;

    public LightLvl getLightLvl() {
        return new LightLvl(lightLevel, timestamp);
    }
}
