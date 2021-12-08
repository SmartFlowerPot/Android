package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceResponse {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("https://smartflowerpot.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static ApplicationAPI applicationApi = retrofit.create(ApplicationAPI.class);
    private static ServiceResponse instance;

    private ServiceResponse(){}

    public static ServiceResponse getInstance(){
        if(instance == null)
            instance = new ServiceResponse();

        return instance;
    }

    public static ApplicationAPI getPlantAPI() {
        return applicationApi;
    }
}
