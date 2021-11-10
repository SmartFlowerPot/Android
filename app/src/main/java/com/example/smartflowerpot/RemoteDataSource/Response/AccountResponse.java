package com.example.smartflowerpot.RemoteDataSource.Response;

import com.example.smartflowerpot.Model.Account;

public class AccountResponse {

    private String password;
    private String username;

    public Account getAccount(String username, String password){
        System.out.println(username + password);
        return new Account(username, password);
    }
}
