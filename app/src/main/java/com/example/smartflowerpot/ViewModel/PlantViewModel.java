package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.Repository.PlantRepo;

public class PlantViewModel extends ViewModel {
    private static PlantRepo plantRepo;

    public PlantViewModel() {
        plantRepo = PlantRepo.getInstance();
    }

    public MutableLiveData<Plant> getPlant() {
        return plantRepo.getPlant();
    }

    public void getPlantInfo(String username, String eui) {
        plantRepo.getPlantInfo(username, eui);
    }
}
