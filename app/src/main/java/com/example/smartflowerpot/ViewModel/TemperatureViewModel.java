package com.example.smartflowerpot.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.Repository.TemperatureRepo;

public class TemperatureViewModel extends ViewModel {
    private static TemperatureRepo temperatureRepo;

    public TemperatureViewModel() {
        temperatureRepo = TemperatureRepo.getInstance();
    }

    public LiveData<Temperature> getTemperature() {
        return temperatureRepo.getTemperature();
    }
}
