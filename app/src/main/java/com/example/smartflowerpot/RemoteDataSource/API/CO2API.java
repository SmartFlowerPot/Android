package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CO2API {
    private static CO2API instance;

    private final MutableLiveData<CO2> currentCO2;

    private CO2API(){
        currentCO2 = new MutableLiveData<>();
        CO2 co2 = new CO2();
        currentCO2.setValue(co2);
    }

    public static synchronized CO2API getInstance(){
        if (instance == null){
            instance = new CO2API();
        }
        return instance;
    }


    public MutableLiveData<CO2> getCO2() {
        return currentCO2;
    }

    public void getCO2Request(String eui) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<CO2Response> call = applicationAPI.getCO2(eui);
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
