package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.smartflowerpot.R;

//  Ionut

public class SettingsFragment extends Fragment {

    private RadioGroup tempUnitsRadioGroup;
    private SharedPreferences prefs;

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences("My Preferences", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initViews(view);

        tempUnitsRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            SharedPreferences.Editor editor = prefs.edit();

            if (i == R.id.celsiusRadioBtn) {
                editor.putString("temp_units", "CELSIUS");
            } else if (i == R.id.fahrenheitRadioBtn) {
                editor.putString("temp_units", "FAHRENHEIT");
            }

            editor.apply();
        });

        return view;
    }

    private void initViews(View view) {
        tempUnitsRadioGroup = view.findViewById(R.id.tempUnitsRadioGroup);

        if (prefs.getString("temp_units", "CELSIUS").equals("FAHRENHEIT")){
            tempUnitsRadioGroup.check(R.id.fahrenheitRadioBtn);
        } else {
            tempUnitsRadioGroup.check(R.id.celsiusRadioBtn);
        }
    }
}