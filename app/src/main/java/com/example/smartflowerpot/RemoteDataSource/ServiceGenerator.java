package com.example.smartflowerpot.RemoteDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("https://smartflowerpot.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static ApplicationAPI applicationApi = retrofit.create(ApplicationAPI.class);
    private static ServiceGenerator instance;

    private ServiceGenerator(){}

    public static ServiceGenerator getInstance(){
        if(instance == null)
            instance = new ServiceGenerator();

        return instance;
    }

    public static ApplicationAPI getPlantAPI() {
        return applicationApi;
    }
}
