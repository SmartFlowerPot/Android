package com.example.smartflowerpot.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.API.TemperatureAPI;


public class TemperatureRepo {
    private static TemperatureRepo instance;

    private MutableLiveData<Temperature> temperature;
    private TemperatureAPI temperatureAPI;

    private TemperatureRepo() {
        temperatureAPI = TemperatureAPI.getInstance();
    }

    public static synchronized TemperatureRepo getInstance() {
        if (instance == null) {
            instance = new TemperatureRepo();
        }
        return instance;
    }

    public MutableLiveData<Temperature> getTemperature() {
        return temperatureAPI.getTemperature();
    }

    public void getTemperatureRequest(String eui) {
        temperatureAPI.getTemperatureRequest(eui);
    }
}
