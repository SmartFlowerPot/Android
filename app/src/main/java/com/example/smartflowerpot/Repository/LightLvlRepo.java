package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.LightLvl;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.CO2Response;
import com.example.smartflowerpot.RemoteDataSource.Response.LightLvlResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightLvlRepo {
    private static LightLvlRepo instance;
    private MutableLiveData<LightLvl> lightLvlResponse;

    public LightLvlRepo() {
        lightLvlResponse = new MutableLiveData<>();
    }

    public static synchronized LightLvlRepo getInstance() {
        if(instance == null)
            instance = new LightLvlRepo();
        return instance;
    }

    public MutableLiveData<LightLvl> getLightLvlResponse() {
        return lightLvlResponse;
    }

    public void getLightLvlRequest(String eui) {
        ApplicationAPI applicationAPI = ServiceGenerator.getApplicationAPI();
        Call<LightLvlResponse> call = applicationAPI.getLightLvl(eui);
        call.enqueue(new Callback<LightLvlResponse>() {
            @Override
            public void onResponse(Call<LightLvlResponse> call, Response<LightLvlResponse> response) {
                if (response.isSuccessful()) {
                    lightLvlResponse.setValue(response.body().getLightLvl());
                }
            }
            @Override
            public void onFailure(Call<LightLvlResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
