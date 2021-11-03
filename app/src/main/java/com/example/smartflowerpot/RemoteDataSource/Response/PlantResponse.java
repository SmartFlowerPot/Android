package com.example.smartflowerpot.RemoteDataSource.Response;

import android.telecom.Call;

import com.example.smartflowerpot.Model.Temperature;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantResponse {
    @SerializedName("data")
    @Expose()
    private Temperature temperature;

    public Temperature getTemperature() {
        return temperature;
    }

    public Call<Temperature> getTemperature(String name) {
        return null;
    }
}
