package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//  Ionut

public class HumidityRepo {
    private static HumidityRepo instance;
    private MutableLiveData<Humidity> humidity;
    private MutableLiveData<ArrayList<Humidity>> humidityArray;

    private HumidityRepo() {
        humidity = new MutableLiveData<>();
    }

    public static HumidityRepo getInstance() {
        if (instance == null) {
            instance = new HumidityRepo();
        }

        return instance;
    }

    public LiveData<ArrayList<Humidity>> getWeekHumidity() {
        return humidityArray;
    }

    public LiveData<Humidity> getHumidity() {
        return humidity;
    }

    public void getWeekHumidityRequest(String deviceID) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<HumidityResponse> call = applicationAPI.getHumidity(deviceID);
        call.enqueue(new Callback<HumidityResponse>() {
            @Override
            public void onResponse(Call<HumidityResponse> call, Response<HumidityResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 204) {
                        humidityArray.setValue(null);
                    } else {

                        //TODO
                    }
                }
            }

            @Override
            public void onFailure(Call<HumidityResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong");
            }
        });
    }

    public void getHumidityRequest(String deviceID) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
        Call<HumidityResponse> call = applicationAPI.getHumidity(deviceID);
        call.enqueue(new Callback<HumidityResponse>() {
            @Override
            public void onResponse(Call<HumidityResponse> call, Response<HumidityResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 204) {
                        humidity.setValue(null);
                    } else {
                        System.out.println(response.body().getHumidity());
                        humidity.setValue(response.body().getHumidity());
                    }
                }
            }

            @Override
            public void onFailure(Call<HumidityResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong");
            }
        });
    }

}
