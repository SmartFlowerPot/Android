package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Call;

import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;

import retrofit2.http.GET;

public interface PlantAPI {

    @GET("api/v2/pokemon/")
    Call<TemperatureResponse> getTemperature();
}
