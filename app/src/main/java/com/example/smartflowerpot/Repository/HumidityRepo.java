package com.example.smartflowerpot.Repository;

import androidx.lifecycle.LiveData;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.RemoteDataSource.API.HumidityAPI;

import java.util.ArrayList;

//  Ionut

public class HumidityRepo {
    private static HumidityRepo instance;
    private HumidityAPI humidityAPI;

    private HumidityRepo() {
        humidityAPI = HumidityAPI.getInstance();
    }

    public static HumidityRepo getInstance() {
        if (instance == null) {
            instance = new HumidityRepo();
        }
        return instance;
    }

    public LiveData<ArrayList<Humidity>> getWeekHumidity() {
        return humidityAPI.getWeekHumidity();
    }

    public LiveData<Humidity> getHumidity() {
        return humidityAPI.getHumidity();
    }

    public void getWeekHumidityRequest(String deviceID) {
        humidityAPI.getWeekHumidityRequest(deviceID);
    }

    public void getHumidityRequest(String deviceID) {
        humidityAPI.getHumidityRequest(deviceID);
    }

}
