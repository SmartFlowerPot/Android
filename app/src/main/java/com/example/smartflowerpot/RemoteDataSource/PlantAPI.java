package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Call;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlantAPI {


    //-----------------------------------------------------------------------------------------------

    @GET("Account/{username}")
    Call<AccountResponse> getAccountByUsername(@Path("username") String username);

    @GET("Account")
    Call<AccountResponse> getAccount(@Query("username") String username, @Query("password") String password);

    @POST("Account")
    Call<AccountResponse> registerAccount(@Body Account account);

    @DELETE("Account")
    Call<AccountResponse> deleteAccount(@Path("username") String username);

    //-----------------------------------------------------------------------------------------------

    @GET("Temperature")
    Call<TemperatureResponse> getTemperature(@Query("eui") String eui);

    //-----------------------------------------------------------------------------------------------

    @GET("CO2")
    Call<CO2Response> getCO2(@Query("eui") String eui);

    //-----------------------------------------------------------------------------------------------

    @GET("Humidity")
    Call<HumidityResponse> getHumidity(@Query("eui") String eui);



}
