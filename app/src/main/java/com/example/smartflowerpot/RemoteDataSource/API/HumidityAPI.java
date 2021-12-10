package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HumidityAPI {
    private static HumidityAPI instance;

    private MutableLiveData<Humidity> humidity;
    private MutableLiveData<ArrayList<Humidity>> humidityArray;

    private HumidityAPI(){
        humidity = new MutableLiveData<>();
        humidityArray = new MutableLiveData<>();
    }

    public static HumidityAPI getInstance() {
        if (instance == null){
            instance = new HumidityAPI();
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
        Call<ArrayList<HumidityResponse>> call = applicationAPI.getWeekHumidity(deviceID);
        call.enqueue(new Callback<ArrayList<HumidityResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<HumidityResponse>> call, Response<ArrayList<HumidityResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        ArrayList<Humidity> temp = new ArrayList<>();
                        Log.d("Humidity Readings", "Getting Readings...");
                        for (int i = 0; i < response.body().size(); i++){

                            temp.add(response.body().get(i).getHumidity());
                            System.out.println(temp.get(i));
                        }
                        humidityArray.setValue(temp);

                    } else {
                    }
                }
                else {
                    Log.d("Humidity Readings", "Error getting readings - User does not have measurements or critical error");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<HumidityResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong - Humidity Readings");
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
