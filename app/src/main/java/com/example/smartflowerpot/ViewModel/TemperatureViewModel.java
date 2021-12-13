package com.example.smartflowerpot.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;
import com.example.smartflowerpot.Repository.TemperatureRepo;

import java.util.ArrayList;

public class TemperatureViewModel extends ViewModel {
    private static TemperatureRepo temperatureRepo;

    public TemperatureViewModel() {
        temperatureRepo = TemperatureRepo.getInstance();
    }

    public MutableLiveData<Temperature> getTemperature() {
        return temperatureRepo.getTemperature();
    }

    public void getTemperatureRequest(String eui) {
        temperatureRepo.getTemperatureRequest(eui);
    }

    public LiveData<ArrayList<Temperature>> getWeekTemperature(){
        return temperatureRepo.getWeekTemperature();
    }
    public void getWeekTemperatureRequest(String eui){
        temperatureRepo.getWeekTemperatureRequest(eui);
    }
}
