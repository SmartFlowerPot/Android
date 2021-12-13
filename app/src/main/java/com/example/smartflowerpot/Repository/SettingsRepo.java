package com.example.smartflowerpot.Repository;

import android.app.Application;

import com.example.smartflowerpot.database.SettingsDAO;

public class SettingsRepo {
    private static SettingsRepo instance;
    private SettingsDAO settingsDAO;

    private SettingsRepo(Application app){
        settingsDAO = SettingsDAO.getInstance(app);
    }

    public static SettingsRepo getInstance(Application app){
        if (instance == null){
            instance = new SettingsRepo(app);
        }
        return instance;
    }

    public void persistTemperatureUnits(String unit) {
        settingsDAO.persistTemperatureUnits(unit);
    }

    public String getPersistedTemperatureUnits() {
        return settingsDAO.getPersistedTemperatureUnits();
    }
}
