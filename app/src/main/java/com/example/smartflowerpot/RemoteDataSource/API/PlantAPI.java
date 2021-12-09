package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantAPI {
    private static PlantAPI instance;

    private MutableLiveData<Plant> plant;
    private MutableLiveData<List<Plant>> plants;
    private MutableLiveData<Plant> createdPlant;

    private PlantAPI(){
        plant = new MutableLiveData<>();
        plants = new MutableLiveData<>();
        createdPlant = new MutableLiveData<>();
    }

    public static synchronized PlantAPI getInstance(){
        if (instance == null){
            instance = new PlantAPI();
        }
        return instance;
    }

    public MutableLiveData<Plant> getPlant() {
        return plant;
    }

    public void getPlantInfo(String plantID) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<PlantResponse> call = applicationAPI.getPlantInfo(plantID);
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

    public void getPlants(String username) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<AccountResponse> call = applicationAPI.getAccountByUsername(username);
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        plants.setValue(null);
                    }
                    else {
                        System.out.println("///////////////////");
                        System.out.println(response.body().getAccount().toString());
                        System.out.println("///////////////////");
                        plants.setValue(response.body().getAccount().getPlants());
                    }
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.i("Retrofit", "Something went wrong with getting plants :(");
                plants.setValue(null);
            }
        });
    }


    public void createAPlant(String username, Plant plant) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<PlantResponse> call = applicationAPI.createAPlant(username, plant);
        call.enqueue(new Callback<PlantResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        createdPlant.setValue(null);

                    }
                    else
                    {
                        Log.d("Creating Plant", username + "/" + plant.toString());
                        createdPlant.setValue(response.body().getPlant());
                        System.out.println(createdPlant.getValue().toString());
                    }
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
