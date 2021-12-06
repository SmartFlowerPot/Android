package com.example.smartflowerpot.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.Repository.AccountRepo;
import com.example.smartflowerpot.Repository.PlantsOverviewRepo;

import java.util.List;

public class PlantsOverviewViewModel extends ViewModel {
    private static PlantsOverviewRepo plantsOverviewRepo;

    public PlantsOverviewViewModel() {
        plantsOverviewRepo = PlantsOverviewRepo.getInstance();
    }

    public MutableLiveData<List<Plant>> getPlantsResponse() {
        return plantsOverviewRepo.getPlants();
    }

    public void getPlants(String username) {
        plantsOverviewRepo.getPlants(username);
    }

    public void createAPlant(String username, Plant plant) {
        plantsOverviewRepo.createAPlant(username, plant);
    }
}
