package com.example.smartflowerpot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.PlantViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;

public class PlantFragment extends Fragment {
    private View view;
    private String deviceIdentifier;
    private TextView temperatureReading;
    private PlantViewModel plantViewModel;
    private TemperatureViewModel temperatureViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceIdentifier = requireArguments().getString("DeviceIdentifier");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_plant, container, false);

        ((BaseActivity)getActivity()).setTopbarTitle("Plant name here");

        temperatureReading = view.findViewById(R.id.temperatureReading);

        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);

        //plantViewModel.getPlantInfo("karlo", deviceIdentifier);

        plantViewModel.getPlant().observe(getViewLifecycleOwner(), new Observer<Plant>() {
            @Override
            public void onChanged(Plant plant) {
                deviceIdentifier = plant.getPlantID();
                temperatureViewModel.getTemperatureRequest();
            }
        });

        temperatureViewModel.getTemperature().observe(getViewLifecycleOwner(), new Observer<Temperature>() {
            @Override
            public void onChanged(Temperature temperature) {
                temperatureReading.setText(String.valueOf(temperature.getTemperature()));
            }
        });

        return view;
    }
}