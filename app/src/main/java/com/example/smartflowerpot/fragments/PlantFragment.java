package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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
import com.example.smartflowerpot.ViewModel.SettingsViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

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
    private SettingsViewModel settingsViewModel;

    private LineChart temperatureChart;
    private LineChart co2Chart;
    private LineChart humidityChart;

    private ArrayList<Humidity> humidityArrayList = new ArrayList<>();
    private ArrayList<CO2> co2ArrayList;
    private ArrayList<Temperature> temperatureArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        temperatureChart.setData(null);
        co2Chart.setData(null);
        humidityChart.setData(null);
        updatePlantInfo();
        super.onResume();
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
                ((BaseActivity) getActivity()).setTopbarTitle(plant.getNickname());
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

                if (settingsViewModel.getPersistedTemperatureUnits().equals("FAHRENHEIT")) {
                    temperatureReading.setText(temperature.getFahrenheitReading());
                } else {
                    temperatureReading.setText(temperature.getCelsiusReading());
                }
            }
        });
        updatePlantInfo();

        humidityViewModel.getWeekHumidity().observe(getViewLifecycleOwner(), new Observer<ArrayList<Humidity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ArrayList<Humidity> humidities) {
                if (humidities == null) {
                    LineGraph lineGraph1 = null;
                    lineGraph1.setupHumidityGraph(null);
                    System.out.println("Humidity NULL");
                } else { ;
                    humidityArrayList = humidities;
                    LineGraph lineGraph1 = new LineGraph(humidityChart, getResources());
                    lineGraph1.setupHumidityGraph(humidityArrayList);
                }
            }
        });
        co2ViewModel.getWeekCO2().observe(getViewLifecycleOwner(), new Observer<ArrayList<CO2>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ArrayList<CO2> co2Array) {
                if (co2Array == null) {
                    LineGraph lineGraph2 = null;
                    lineGraph2.setupCO2Graph(null);
                    System.out.println("CO2 NULL");
                } else {
                    co2ArrayList = co2Array;
                    LineGraph lineGraph2 = new LineGraph(co2Chart, getResources());
                    lineGraph2.setupCO2Graph(co2ArrayList);
                }
            }
        });
        temperatureViewModel.getWeekTemperature().observe(getViewLifecycleOwner(), new Observer<ArrayList<Temperature>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ArrayList<Temperature> tempArray) {
                if (tempArray == null) {
                    LineGraph lineGraph3 = null;
                    lineGraph3.setupTemperatureGraph(null);
                    System.out.println("Temperature NULL");
                } else {
                    temperatureArrayList = tempArray;
                    LineGraph lineGraph3 = new LineGraph(temperatureChart, getResources());
                    lineGraph3.setupTemperatureGraph(temperatureArrayList);
                }
            }
        });



        return view;
    }

    private void updatePlantInfo() {
        deviceIdentifier = requireArguments().getString("DeviceIdentifier");

        temperatureReading.setText(R.string.no_reading);
        co2Reading.setText(R.string.no_reading);
        humidityReading.setText(R.string.no_reading);

        plantViewModel.getPlantInfo(deviceIdentifier);
        temperatureViewModel.getTemperatureRequest(deviceIdentifier);
        humidityViewModel.getHumidityRequest(deviceIdentifier);
        co2ViewModel.getCO2Request(deviceIdentifier);

        humidityViewModel.getWeekHumidityRequest(deviceIdentifier);
        co2ViewModel.getWeekCO2Request(deviceIdentifier);
        temperatureViewModel.getWeekTemperatureRequest(deviceIdentifier);
    }

    private void getViewModels() {
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        humidityViewModel = new ViewModelProvider(this).get(HumidityViewModel.class);
        co2ViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
    }

    private void initViews() {
        temperatureReading = view.findViewById(R.id.temperatureReading);
        co2Reading = view.findViewById(R.id.co2Reading);
        humidityReading = view.findViewById(R.id.humidityReading);
        co2Chart = view.findViewById(R.id.co2Chart);
        temperatureChart = view.findViewById(R.id.temperatureChart);
        humidityChart = view.findViewById(R.id.humidityChart);

    }

}