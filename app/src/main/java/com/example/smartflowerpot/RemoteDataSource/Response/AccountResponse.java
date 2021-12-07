package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Account;
import com.example.smartflowerpot.Model.Plant;

import java.util.ArrayList;

public class AccountResponse {

    private String password;
    private String username;
    private ArrayList<Plant> plants;

    public Account getAccount() {
        return new Account(username, password, plants);
    }

    public Account getAccount(String username, String password) {
        return new Account(username, password);
    }
}
