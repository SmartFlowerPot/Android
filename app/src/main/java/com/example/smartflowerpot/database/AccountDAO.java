package com.example.smartflowerpot.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AccountDAO {
    private static AccountDAO instance;
    private  Application application;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private AccountDAO(Application app){
        this.application = app;
        prefs = application.getSharedPreferences("AccountPreferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }


    public static AccountDAO getInstance(Application app){
        if (instance == null){
            instance = new AccountDAO(app);
        }
        return instance;
    }

    public void persistLoggedInUser(String username) {
        editor.putString("username", username);
        editor.apply();
    }

    public String getPersistedLoggedInUser() {
        String username = prefs.getString("username", "none");
        if (username.equals("none")){
            return null;
        } return username;
    }

    public void discontinueLoggedInUser(String username) {
        editor.remove("username");
        editor.apply();
    }
}
