package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Call;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.HumidityResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.LightLvlResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.PlantResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApplicationAPI {


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

    @GET("Plant")
    Call<PlantResponse> getPlantInfo(@Query("eui") String eui);

    //-----------------------------------------------------------------------------------------------

    @GET("CO2")
    Call<CO2Response> getCO2(@Query("eui") String eui);

    //-----------------------------------------------------------------------------------------------

    @GET("Temperature/history")
    Call<ArrayList<TemperatureResponse>> getWeekTemperature(@Query("eui") String eu);
    @GET("CO2/history")
    Call<ArrayList<CO2Response>> getWeekCO2(@Query("eui") String eu);
    @GET("Humidity/history")
    Call<ArrayList<HumidityResponse>> getWeekHumidity(@Query("eui") String eu);

    //  Ionut
    @GET("Humidity")
    Call<HumidityResponse> getHumidity(@Query("eui") String eui);

    @GET("Account/{username}")
    Call<AccountResponse> getPlants(@Path("username") String username);

    @POST("Plant/{username}")
    Call<PlantResponse> createAPlant(@Path("username") String username, @Body Plant plant);

    //Antonio
    @POST("Window")
    Call<CO2Response> ControlWindow( @Query("eui") String eui,
                                    @Query("open_close_window") int open_close_window);


    //Antonio
    @DELETE("plant")
    Call<PlantResponse> deletePlant(@Query("eui") String eui);
    //-------------------------Karlo------------------------
    @GET("Light")
    Call<LightLvlResponse> getLightLvl(@Query("eui") String eui);
    //------------------------------------------------------
}
