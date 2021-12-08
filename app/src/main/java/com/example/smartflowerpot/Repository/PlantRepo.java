package com.example.smartflowerpot.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.PlantAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;
import com.example.smartflowerpot.database.PlantDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantRepo {
    private static PlantRepo instance;
    private MutableLiveData<Plant> plant;
    private MutableLiveData<List<Plant>> plants;
    private MutableLiveData<Plant> createdPlant;

    private PlantRepo(Application application) {
        PlantDatabase database = PlantDatabase.getInstance(application);


        plant = new MutableLiveData<>();
        plants = new MutableLiveData<>();
        createdPlant = new MutableLiveData<>();
    }

    public static synchronized PlantRepo getInstance(Application application) {
        if(instance == null)
            instance = new PlantRepo(application);
        return instance;
    }

    public MutableLiveData<Plant> getPlant() {
        return plant;
    }

    public void getPlantInfo(String plantID) {
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Call<PlantResponse> call = plantAPI.getPlantInfo(plantID);
        call.enqueue(new Callback<PlantResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        plant.setValue(null);
                    }
                    else {
                        System.out.println(response.body().getPlant());
                        plant.setValue(response.body().getPlant());
                    }
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                plant.setValue(null);
            }
        });
    }

    public MutableLiveData<List<Plant>> getPlants() {
        return plants;
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
