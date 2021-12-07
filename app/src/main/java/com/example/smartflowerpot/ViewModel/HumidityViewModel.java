package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Repository.HumidityRepo;

//  Ionut

public class HumidityViewModel extends ViewModel {
    private static HumidityRepo humidityRepo;

    public HumidityViewModel() {humidityRepo = HumidityRepo.getInstance();}

    public LiveData<Humidity> getHumidity(){
        return humidityRepo.getHumidity();
    }

    public void getHumidityRequest(String deviceID){
        humidityRepo.getHumidityRequest(deviceID);
    }
}
