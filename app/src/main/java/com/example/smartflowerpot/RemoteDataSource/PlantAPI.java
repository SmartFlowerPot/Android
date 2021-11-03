package com.example.smartflowerpot.RemoteDataSource;

import android.telecom.Call;

import com.example.smartflowerpot.Repository.TemperatureResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlantAPI {

    @GET("api/v2/pokemon/{name}")
    Call<TemperatureResponse> getTemperature(@Path("name") String name);
}
