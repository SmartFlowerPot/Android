package com.example.smartflowerpot.RemoteDataSource;

public class ServiceResponse {
    private static ServiceResponse instance;

    private ServiceResponse(){
        instance = ServiceResponse.getInstance();
    }

    public static ServiceResponse getInstance(){
        if(instance == null)
            instance = new ServiceResponse();

        return instance;
    }
}
