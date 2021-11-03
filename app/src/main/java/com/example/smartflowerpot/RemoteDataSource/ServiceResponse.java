package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceResponse {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("https://pokeapi.co").addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static PlantAPI plantApi = retrofit.create(PlantAPI.class);
    private static ServiceResponse instance;

    private ServiceResponse(){
        instance = ServiceResponse.getInstance();
    }

    public static ServiceResponse getInstance(){
        if(instance == null)
            instance = new ServiceResponse();

        return instance;
    }

    public static PlantAPI getPlantAPI() {
        return plantApi;
    }
}
