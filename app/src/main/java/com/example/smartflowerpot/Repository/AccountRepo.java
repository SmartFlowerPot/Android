package com.example.smartflowerpot.Repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
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

public class AccountRepo {
    private static AccountRepo instance;
    private MutableLiveData<Account> account;
    private MutableLiveData<List<Plant>> plants;

    Application application;

    private AccountRepo(Application app) {
        this.application = app;
        account = new MutableLiveData<>();
        plants = new MutableLiveData<>();
    }

    public LiveData<Account> getCurrentAccount() {
        return account;
    }

    public static synchronized AccountRepo getInstance(Application app) {
        if (instance == null) {
            instance = new AccountRepo(app);
        }
        return instance;
    }

    public LiveData<Account> getAccount(String username, String password) {
        getAccountRequest(username, password);
        return account;
    }

    public LiveData<List<Plant>> getAllCurrentPlants(){
        return plants;
    }

    private void getAccountRequest(String username, String password) {
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
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
        ApplicationAPI applicationAPI = ServiceGenerator.getPlantAPI();
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

    public void persistLoggedInUser(String username) {
        SharedPreferences prefs = application.getSharedPreferences("AccountPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.apply();
        //TODO move this to accountDAO
    }

    public String getPersistedLoggedInUser() {
        SharedPreferences prefs = application.getSharedPreferences("AccountPreferences", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "none");
        if (username.equals("none")){
            return null;
        } return username;
    }

    public void discontinueLoggedInUser(String username) {
        SharedPreferences prefs = application.getSharedPreferences("AccountPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("username");
        editor.apply();
    }
}
