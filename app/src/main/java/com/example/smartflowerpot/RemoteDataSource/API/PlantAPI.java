package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantAPI {
    private static PlantAPI instance;
    ApplicationAPI applicationAPI;

    private MutableLiveData<Plant> plant;
    private MutableLiveData<List<Plant>> plants;
    private MutableLiveData<Plant> createdPlant;
    private MutableLiveData<CO2> Co2;
    private PlantAPI(){
        plant = new MutableLiveData<>();
        plants = new MutableLiveData<>();
        createdPlant = new MutableLiveData<>();
        Co2 = new MutableLiveData<>();
        applicationAPI = ServiceGenerator.getApplicationAPI();
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
        Call<AccountResponse> call = applicationAPI.getAccountByUsername(username);
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        plants.setValue(null);
                    } else {
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

    public void ControlWindow(String eui, int toOpen) {
        ApplicationAPI applicationAPI = ServiceGenerator.getApplicationAPI();
        Call call = applicationAPI.ControlWindow(eui, toOpen);
        call.enqueue(new Callback() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    Log.d("Response for Window", response.code() + "");
                }
                else {
                    Log.d("Response for Window", "The current state of the connection is not Open.");
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
    public void deletePlant(String eui) {
        ApplicationAPI applicationAPI = ServiceGenerator.getApplicationAPI();
        Call<PlantResponse> call = applicationAPI.deletePlant(eui);
        call.enqueue(new Callback<PlantResponse>() {
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 204) {

                    }
                } else {
                    Log.d("Response", "Does not work");
                }
            }
            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

}
