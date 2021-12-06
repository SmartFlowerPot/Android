package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.RemoteDataSource.PlantAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CO2Repo {
    private static CO2Repo instance;
    private final MutableLiveData<CO2> currentCO2;

    private CO2Repo() {
        currentCO2 = new MutableLiveData<>();
    }

    public static synchronized CO2Repo getInstance() {
        if (instance == null) {
            instance = new CO2Repo();
        }
        return instance;
    }

    public MutableLiveData<CO2> getCO2() {
        return currentCO2;
    }

    public void getCO2Request() {
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Call<CO2Response> call = plantAPI.getCO2();
        call.enqueue(new Callback<CO2Response>() {
            @Override
            public void onResponse(Call<CO2Response> call, Response<CO2Response> response) {
                if (response.isSuccessful()) {
                    currentCO2.setValue(response.body().getCO2());
                }
            }

            @Override
            public void onFailure(Call<CO2Response> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                currentCO2.setValue(null);
            }
        });


    }
}


