package com.example.smartflowerpot.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.RemoteDataSource.PlantAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.Response.TemperatureResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AccountRepo {
    private static AccountRepo instance;
    private final MutableLiveData<Account> account;


    private AccountRepo() {
        account = new MutableLiveData<>();
    }

    public static synchronized AccountRepo getInstance() {
        if (instance == null) {
            instance = new AccountRepo();
        }
        return instance;
    }

    public LiveData<Account> getAccount(String username, String password) {
        getAccountRequest(username, password);
        return account;
    }

    public void getAccountRequest(String username, String password) {
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Call<AccountResponse> call = plantAPI.getAccount(username, password);
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    account.setValue(response.body().getAccount(username, username));
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public LiveData<Account> registerAccount(String username, String password) {
        registerAccountRequest(username, password);
        return account;
    }
    public void registerAccountRequest(String username, String password) {
        PlantAPI plantAPI = ServiceResponse.getPlantAPI();
        Account tempAccount = new Account(username, password);
        Call<AccountResponse> call = plantAPI.registerAccount(tempAccount);
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    account.setValue(response.body().getAccount(username, password));
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
