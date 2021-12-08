package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.CO2ViewModel;
import com.example.smartflowerpot.ViewModel.HumidityViewModel;
import com.example.smartflowerpot.ViewModel.PlantViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;

public class PlantFragment extends Fragment {
    private View view;
    private String deviceIdentifier;
    private TextView temperatureReading;
    private TextView co2Reading;
    private TextView humidityReading;

    private PlantViewModel plantViewModel;
    private TemperatureViewModel temperatureViewModel;
    private HumidityViewModel humidityViewModel;
    private CO2ViewModel co2ViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        updatePlantInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plant, container, false);

        initViews();

        getViewModels();

        plantViewModel.getPlant().observe(getViewLifecycleOwner(), new Observer<Plant>() {
            @Override
            public void onChanged(Plant plant) {
                System.out.println(plant);
                deviceIdentifier = plant.getEui();
                ((BaseActivity)getActivity()).setTopbarTitle(plant.getNickname());
            }
        });

        humidityViewModel.getHumidity().observe(getViewLifecycleOwner(), new Observer<Humidity>() {
            @Override
            public void onChanged(Humidity humidity) {
                humidityReading.setText(humidity.getReading());
            }
        });

        co2ViewModel.getCO2().observe(getViewLifecycleOwner(), new Observer<CO2>() {
            @Override
            public void onChanged(CO2 co2) {
                co2Reading.setText(co2.getReading());
            }
        });

        temperatureViewModel.getTemperature().observe(getViewLifecycleOwner(), new Observer<Temperature>() {
            @Override
            public void onChanged(Temperature temperature) {

                SharedPreferences prefs = getActivity().getSharedPreferences("My Preferences", Context.MODE_PRIVATE);
                if (prefs.getString("temp_units", "CELSIUS").equals("FAHRENHEIT")) {
                    temperatureReading.setText(temperature.getFahrenheitReading());
                } else {
                    temperatureReading.setText(temperature.getCelsiusReading());
                }
            }
        });

        updatePlantInfo();

        return view;
    }

    private void updatePlantInfo() {
        deviceIdentifier = requireArguments().getString("DeviceIdentifier");

        temperatureReading.setText(R.string.no_reading);
        co2Reading.setText(R.string.no_reading);
        humidityReading.setText(R.string.no_reading);

        plantViewModel.getPlantInfo("karlo", deviceIdentifier); // TODO fix this hardcoded username
        temperatureViewModel.getTemperatureRequest(deviceIdentifier);
        humidityViewModel.getHumidityRequest(deviceIdentifier);
        co2ViewModel.getCO2Request(deviceIdentifier);
    }

    private void getViewModels() {
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        humidityViewModel = new ViewModelProvider(this).get(HumidityViewModel.class);
        co2ViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
    }

    private void initViews() {
        temperatureReading = view.findViewById(R.id.temperatureReading);
        co2Reading = view.findViewById(R.id.co2Reading);
        humidityReading = view.findViewById(R.id.humidityReading);
    }
}