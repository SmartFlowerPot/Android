package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.RemoteDataSource.API.PlantAPI;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.RemoteDataSource.API.CO2API;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CO2Repo {
    private static CO2Repo instance;
    String TAG = "Requesting CO2: ";
    private PlantAPI plantAPI;
    private CO2API co2API;

    private CO2Repo() {
        co2API = CO2API.getInstance();
    }

    public static synchronized CO2Repo getInstance() {
        if (instance == null) {
            instance = new CO2Repo();
        }
        return instance;
    }

    public MutableLiveData<CO2> getCO2() {
        return co2API.getCO2();
    }

    public void getCO2Request(String eui) {
        co2API.getCO2Request(eui);
    }



    public void ControlWindow( String eui, int open_close_window) {
        plantAPI.ControlWindow(eui, open_close_window);
    }
    public LiveData<ArrayList<CO2>> getWeekCO2() {
        return co2API.getWeekCO2();
    }
    public void getWeekCO2yRequest(String eui) {
        co2API.getWeekCO2Request(eui);
    }
}


