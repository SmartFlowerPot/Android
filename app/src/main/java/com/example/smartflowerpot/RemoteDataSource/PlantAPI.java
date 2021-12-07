package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Call;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantsResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;

import retrofit2.http.Body;
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

    //-----------------------------------------------------------------------------------------------

    @GET("Temperature")
    Call<TemperatureResponse> getTemperature();

    @GET("Temperature/iot")
    Call<TemperatureResponse> getTemperatureFromIot();

    //-----------------------------------------------------------------------------------------------

    @GET("Plant/{username}&{plantID}")
    Call<PlantResponse> getPlantInfo(@Path("username") String username, @Path("plantID") String plantID);

    //  Ionut
    @GET("Humidity")
    Call<HumidityResponse> getHumidity(@Query("eui") String eui);

    @GET("Plant/{username}")
    Call<PlantsResponse> getPlants(@Path("username") String username);

    @POST("Plant/{username}/{plant}")
    Call<PlantResponse> createAPlant(@Path("username") String username, @Path("plant") Plant plant);
}
