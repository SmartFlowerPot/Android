package com.example.smartflowerpot.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SettingsDAO {
    private static SettingsDAO instance;
    private Application application;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private SettingsDAO(Application app){
        this.application = app;
        prefs = application.getSharedPreferences("SettingsPreferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static SettingsDAO getInstance(Application app){
        if (instance == null){
            instance = new SettingsDAO(app);
        }
        return instance;
    }


    public void persistTemperatureUnits(String unit) {
        editor.putString("temperatureUnits", unit);
        editor.apply();
    }

    public String getPersistedTemperatureUnits() {
        return prefs.getString("temperatureUnits", "CELSIUS");
    }
}
