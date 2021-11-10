package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Call;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlantAPI {


    //-----------------------------------------------------------------------------------------------

    @GET("/Account/{username}")
    Call<AccountResponse> getAccountByUsername(@Path("username") String username);

    @GET("/Account/{username}&{password}")
    Call<AccountResponse> getAccount(@Path("username") String username, @Path("password") String password);

    @POST("/Account")
    Call<AccountResponse> registerAccount(@Body Account account);

    //-----------------------------------------------------------------------------------------------

    @GET("/Temperature")
    Call<TemperatureResponse> getTemperature();

    //-----------------------------------------------------------------------------------------------



}
