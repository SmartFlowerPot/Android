package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TemperatureAPI {
    private static TemperatureAPI instance;
    private MutableLiveData<Temperature> temperature;
    private MutableLiveData<ArrayList<Temperature>> temperatureArray;

    private TemperatureAPI (){
        temperature = new MutableLiveData<>();
        temperatureArray = new MutableLiveData<>();
    }

    public static TemperatureAPI getInstance(){
        if (instance == null){
            instance = new TemperatureAPI();
        }
        return instance;
    }

    public MutableLiveData<Temperature> getTemperature() {
        return temperature;
    }

    public void getTemperatureRequest(String eui) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<TemperatureResponse> call = applicationAPI.getTemperature(eui);
        call.enqueue(new Callback<TemperatureResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<TemperatureResponse> call, Response<TemperatureResponse> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 204) {
                        temperature.setValue(null);
                    }
                    else {
                        temperature.setValue(response.body().getTemperature());
                    }
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<TemperatureResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                temperature.setValue(null);
            }
        });
    }
    public LiveData<ArrayList<Temperature>> getWeekTemperature() {
        return temperatureArray;
    }
    public void getWeekTemperatureRequest(String deviceID) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<ArrayList<TemperatureResponse>> call = applicationAPI.getWeekTemperature(deviceID);
        call.enqueue(new Callback<ArrayList<TemperatureResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TemperatureResponse>> call, Response<ArrayList<TemperatureResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        ArrayList<Temperature> temp = new ArrayList<>();
                        Log.d("Temperature Readings", "Getting Readings...");
                        for (int i = 0; i < response.body().size(); i++){

                            temp.add(response.body().get(i).getTemperature());
                        }
                        temperatureArray.setValue(temp);

                    } else {
                    }
                }
                else {
                    Log.d("Temperature Readings", "Error getting readings - User does not have measurements or critical error");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<TemperatureResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong - Temperature Readings");
            }
        });
    }
}
