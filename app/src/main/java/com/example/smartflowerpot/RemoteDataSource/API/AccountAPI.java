package com.example.smartflowerpot.RemoteDataSource.API;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AccountAPI {
    private static AccountAPI instance;

    private MutableLiveData<Account> account;
    private MutableLiveData<List<Plant>> plants;

    private AccountAPI(){
        account = new MutableLiveData<>();
        plants = new MutableLiveData<>();
    }

    public static synchronized AccountAPI getInstance(){
        if (instance == null){
            instance = new AccountAPI();
        }
        return instance;
    }

    public LiveData<Account> getCurrentAccount() {
        return account;
    }

    public LiveData<Account> getAccount(String username, String password) {
        getAccountRequest(username, password);
        return account;
    }

    public LiveData<List<Plant>> getAllCurrentPlants(){
        return plants;
    }

    private void getAccountRequest(String username, String password) {
        ApplicationAPI applicationAPI = ServiceGenerator.getApplicationAPI();
        Call<AccountResponse> call = applicationAPI.getAccount(username, password);
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.code() == 200) {
                    account.setValue(response.body().getAccount(username, password));
                    Log.d("Requesting Account", account.getValue().toString());
                } else if(response.code() == 404){
                    account.setValue(null);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                account.setValue(null);
            }
        });
    }

    public LiveData<Account> registerAccount(String username, String password, String dob, String gender, String region) {
        registerAccountRequest(username, password, dob, gender, region);
        return account;
    }

    private void registerAccountRequest(String username, String password, String dob, String gender, String region) {
        ApplicationAPI applicationAPI = ServiceGenerator.getApplicationAPI();
        Account tempAccount = new Account(username, password, dob, gender, region);
        Call<AccountResponse> call = applicationAPI.registerAccount(tempAccount);
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    account.setValue(response.body().getAccount(username, password));
                    Log.d("Registering Account", account.getValue().toString());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                account.setValue(null);
            }
        });
    }

}
