package com.example.smartflowerpot.RemoteDataSource;

import android.telecom.Call;

import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlantAPI {

    @GET("api/v2/pokemon/{name}")
    Call<PlantResponse> getTemperature(@Path("name") String name);
}
