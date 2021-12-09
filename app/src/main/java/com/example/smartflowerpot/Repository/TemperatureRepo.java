package com.example.smartflowerpot.Repository;

import retrofit2.Call;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TemperatureRepo {
    private static TemperatureRepo instance;
    private MutableLiveData<Temperature> temperature;


    private TemperatureRepo() {
        temperature = new MutableLiveData<>();

    }

    public static synchronized TemperatureRepo getInstance() {
        if (instance == null) {
            instance = new TemperatureRepo();
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
}
