package com.example.smartflowerpot.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.API.TemperatureAPI;

import java.util.ArrayList;


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
    public LiveData<ArrayList<Temperature>> getWeekTemperature() {
        return temperatureAPI.getWeekTemperature();
    }
    public void getWeekHumidityRequest(String deviceID) {
        temperatureAPI.getWeekTemperatureRequest(deviceID);
    }
}
