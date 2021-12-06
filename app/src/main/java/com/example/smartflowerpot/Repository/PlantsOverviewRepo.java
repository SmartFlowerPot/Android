package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.PlantAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantsResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantsOverviewRepo {
    private static PlantsOverviewRepo instance;
    private MutableLiveData<List<Plant>> plants;
    private MutableLiveData<Plant> createdPlant;

    private PlantsOverviewRepo() {
        plants = new MutableLiveData<>();
        createdPlant = new MutableLiveData<>();
    }

    public static synchronized PlantsOverviewRepo getInstance() {
        if(instance == null)
            instance = new PlantsOverviewRepo();
        return instance;
    }

    public MutableLiveData<Plant> getCreatedPlant() {
        return createdPlant;
    }

    public MutableLiveData<List<Plant>> getPlants() {
        return plants;
    }

    public void getPlants(String username) {
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Call<PlantsResponse> call = plantAPI.getPlants(username);
        call.enqueue(new Callback<PlantsResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PlantsResponse> call, Response<PlantsResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        plants.setValue(null);
                    }
                    else plants.setValue(response.body().getPlants());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PlantsResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                plants.setValue(null);
            }
        });
    }

    public void createAPlant(String username, Plant plant) {
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Call<PlantResponse> call = plantAPI.createAPlant(username, plant);
        call.enqueue(new Callback<PlantResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        createdPlant.setValue(null);
                    }
                    else createdPlant.setValue(response.body().getPlant());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                createdPlant.setValue(null);
            }
        });
    }
}
