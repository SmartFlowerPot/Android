package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CO2API {
    private static CO2API instance;

    private final MutableLiveData<CO2> currentCO2;
    private MutableLiveData<ArrayList<CO2>> co2Array;

    private CO2API(){
        currentCO2 = new MutableLiveData<>();
        CO2 co2 = new CO2();
        currentCO2.setValue(co2);
        co2Array = new MutableLiveData<>();
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
    public LiveData<ArrayList<CO2>> getWeekCO2() {
        return co2Array;
    }
    public void getWeekCO2Request(String deviceID) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<ArrayList<CO2Response>> call = applicationAPI.getWeekCO2(deviceID);
        call.enqueue(new Callback<ArrayList<CO2Response>>() {
            @Override
            public void onResponse(Call<ArrayList<CO2Response>> call, Response<ArrayList<CO2Response>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        ArrayList<CO2> temp = new ArrayList<>();
                        Log.d("CO2 Readings", "Getting Readings...");
                        for (int i = 0; i < response.body().size(); i++){

                            temp.add(response.body().get(i).getCO2());
                            System.out.println(temp.get(i));
                        }
                        co2Array.setValue(temp);

                    } else {
                    }
                }
                else {
                    Log.d("CO2 Readings", "Error getting readings - User does not have measurements or critical error");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<CO2Response>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong - CO2 Readings");
            }
        });
    }
}
