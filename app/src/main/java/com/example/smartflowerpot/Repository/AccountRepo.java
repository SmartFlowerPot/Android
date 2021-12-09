package com.example.smartflowerpot.Repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.RemoteDataSource.API.AccountAPI;
import com.example.smartflowerpot.RemoteDataSource.ApplicationAPI;
import com.example.smartflowerpot.RemoteDataSource.Response.AccountResponse;
import com.example.smartflowerpot.RemoteDataSource.ServiceGenerator;
import com.example.smartflowerpot.database.AccountDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AccountRepo {
    private static AccountRepo instance;

    private AccountDAO accountDAO;
    private AccountAPI accountAPI;

    private AccountRepo(Application app) {
        accountDAO = AccountDAO.getInstance(app);
        accountAPI = AccountAPI.getInstance();
    }

    public LiveData<Account> getCurrentAccount() {
        return accountAPI.getCurrentAccount();
    }

    public static synchronized AccountRepo getInstance(Application app) {
        if (instance == null) {
            instance = new AccountRepo(app);
        }
        return instance;
    }

    public LiveData<Account> getAccount(String username, String password) {
        return accountAPI.getAccount(username, password);
    }

    public LiveData<List<Plant>> getAllCurrentPlants(){
        return accountAPI.getAllCurrentPlants();
    }

    public LiveData<Account> registerAccount(String username, String password, String dob, String gender, String region) {
        return accountAPI. registerAccount(username, password, dob, gender, region);
    }


    public void persistLoggedInUser(String username) {
        accountDAO.persistLoggedInUser(username);
    }

    public String getPersistedLoggedInUser() {
        return accountDAO.getPersistedLoggedInUser();
    }

    public void discontinueLoggedInUser(String username) {
        accountDAO.discontinueLoggedInUser(username);
    }
}
