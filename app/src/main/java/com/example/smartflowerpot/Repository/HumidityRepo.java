package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.RemoteDataSource.PlantAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//  Ionut

public class HumidityRepo {
    private static HumidityRepo instance;
    private MutableLiveData<Humidity> humidity;

    private HumidityRepo(){
        humidity = new MutableLiveData<>();
    }

    public static HumidityRepo getInstance(){
        if (instance == null){
            instance = new HumidityRepo();
        }

        return instance;
    }

    public LiveData<Humidity> getHumidity(){
        return humidity;
    }

    public void getHumidityRequest(){
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Call<HumidityResponse> call = plantAPI.getHumidity();
        call.enqueue(new Callback<HumidityResponse>() {
            @Override
            public void onResponse(Call<HumidityResponse> call, Response<HumidityResponse> response) {
                if (response.isSuccessful()){
                    if (response.code() == 204){
                        humidity.setValue(null);
                    } else {
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
