package com.example.smartflowerpot.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.smartflowerpot.Repository.SettingsRepo;

public class SettingsViewModel extends AndroidViewModel {

    private SettingsRepo settingsRepo;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        settingsRepo = SettingsRepo.getInstance(application);
    }

    public void persistTemperatureUnits(String unit) {
        settingsRepo.persistTemperatureUnits(unit);
    }

    public String getPersistedTemperatureUnits() {
        return settingsRepo.getPersistedTemperatureUnits();
    }
}
