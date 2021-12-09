package com.example.smartflowerpot.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AccountDAO {
    private static AccountDAO instance;
    private  Application application;

    private AccountDAO(Application app){
        this.application = app;
    }


    public static AccountDAO getInstance(Application app){
        if (instance == null){
            instance = new AccountDAO(app);
        }
        return instance;
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
