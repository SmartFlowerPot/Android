package com.example.smartflowerpot.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.Repository.PlantRepo;

import java.util.List;

public class PlantViewModel extends AndroidViewModel {
    private static PlantRepo plantRepo;

    public PlantViewModel(@NonNull Application application) {
        super(application);
        plantRepo = PlantRepo.getInstance(application);
    }


    public MutableLiveData<Plant> getPlant() {
        return plantRepo.getPlantFromAPI();
    }

    public void getPlantInfo(String eui) {
        plantRepo.getPlantInfoFromAPI(eui);
    }

    public MutableLiveData<List<Plant>> getPlantsResponseFromAPI() {
        return plantRepo.getPlantsResponseFromAPI();
    }

    public void getPlantsFromAPI(String username) {
        plantRepo.getPlantsFromAPI(username);
    }

    public LiveData<List<Plant>> getPlantsResponseFromDb() {
        return plantRepo.getPlantsFromDB();
    }

    public void createAPlant(String username, Plant plant) {
        plantRepo.createAPlant(username, plant);
    }
}
