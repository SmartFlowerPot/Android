package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.SettingsViewModel;

//  Ionut

public class SettingsFragment extends Fragment {

    private RadioGroup tempUnitsRadioGroup;
    private SettingsViewModel settingsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initViews(view);

        tempUnitsRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == R.id.celsiusRadioBtn) {
                settingsViewModel.persistTemperatureUnits("CELSIUS");
            } else if (i == R.id.fahrenheitRadioBtn) {
                settingsViewModel.persistTemperatureUnits("FAHRENHEIT");
            }
        });

        return view;
    }

    private void initViews(View view) {
        tempUnitsRadioGroup = view.findViewById(R.id.tempUnitsRadioGroup);
        if (settingsViewModel.getPersistedTemperatureUnits().equals("FAHRENHEIT")){
            tempUnitsRadioGroup.check(R.id.fahrenheitRadioBtn);
        } else {
            tempUnitsRadioGroup.check(R.id.celsiusRadioBtn);
        }
    }
}