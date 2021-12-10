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
            @Override
            public void onChanged(ArrayList<Humidity> humidities) {
                if (humidities == null) {
                    LineGraph lineGraph2 = null;
                    lineGraph2.setupHumidityGraph(null);
                    System.out.println("Humidity NULL");
                } else {
                    System.out.println(humidities.toString());
                    humidityArrayList = humidities;
                    LineGraph lineGraph2 = new LineGraph(humidityChart, getResources());
                    lineGraph2.setupHumidityGraph(humidityArrayList);
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

    private void initGraphs() {
        ArrayList<Humidity> humidityTest = new ArrayList();
        ArrayList<CO2> co2Test = new ArrayList();
        ArrayList<Temperature> tempTest = new ArrayList();

        Humidity humidity1 = new Humidity(20, "2021-12-06T01:22:03.329Z");
        Humidity humidity2 = new Humidity(0, "2021-12-07T10:22:03.329Z");
        Humidity humidity3 = new Humidity(30, "2021-12-08T22:22:03.329Z");
        Humidity humidity4 = new Humidity(35, "2021-12-09T14:22:03.329Z");
        humidityTest.add(humidity1);
        humidityTest.add(humidity2);
        humidityTest.add(humidity3);
        humidityTest.add(humidity4);

        CO2 co21 = new CO2(20, "2021-12-06T01:22:03.329Z");
        CO2 co22 = new CO2(200, "2021-12-07T10:22:03.329Z");
        CO2 co23 = new CO2(10, "2021-12-08T22:22:03.329Z");
        CO2 co24 = new CO2(40, "2021-12-09T14:22:03.329Z");
        co2Test.add(co21);
        co2Test.add(co22);
        co2Test.add(co23);
        co2Test.add(co24);

        Temperature temperature1 = new Temperature("2021-12-06T01:22:03.329Z", 22);
        Temperature temperature2 = new Temperature("2021-12-07T10:22:03.329Z", 23);
        Temperature temperature3 = new Temperature("2021-12-08T22:22:03.329Z", 28);
        Temperature temperature4 = new Temperature("2021-12-09T14:22:03.329Z", 26);
        tempTest.add(temperature1);
        tempTest.add(temperature2);
        tempTest.add(temperature3);
        tempTest.add(temperature4);
        LineGraph lineGraph = new LineGraph(temperatureChart, getResources());
        LineGraph lineGraph2 = new LineGraph(humidityChart, getResources());
        LineGraph lineGraph3 = new LineGraph(co2Chart, getResources());

          lineGraph.setupTemperatureGraph(tempTest);
        //lineGraph2.setupHumidityGraph(humidityArrayList);
           lineGraph3.setupCO2Graph(co2Test);
    }

}