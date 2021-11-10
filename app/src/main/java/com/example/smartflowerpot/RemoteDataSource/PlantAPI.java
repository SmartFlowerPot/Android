package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Call;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlantAPI {

    @GET("/Temperature")
    Call<TemperatureResponse> getTemperature();
    @GET("/Account/{username}")
    Call<AccountResponse> getAccount(@Path("username") String username);
    @POST("/Account")
    Call<AccountResponse> postAccount();
}
