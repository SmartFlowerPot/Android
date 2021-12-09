package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Repository.HumidityRepo;

import java.util.ArrayList;

//  Ionut

public class HumidityViewModel extends ViewModel {
    private static HumidityRepo humidityRepo;

    public HumidityViewModel() {humidityRepo = HumidityRepo.getInstance();}

    public LiveData<Humidity> getHumidity(){
        return humidityRepo.getHumidity();
    }

    public void getHumidityRequest(String eui){
        humidityRepo.getHumidityRequest(eui);
    }

    public LiveData<ArrayList<Humidity>> getWeekHumidity(){
        return humidityRepo.getWeekHumidity();
    }
    public void getWeekHumidityRequest(String eui){
        humidityRepo.getWeekHumidityRequest(eui);
    }
}
