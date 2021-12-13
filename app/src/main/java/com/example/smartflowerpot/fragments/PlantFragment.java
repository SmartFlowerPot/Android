package com.example.smartflowerpot.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.smartflowerpot.Model.LightLvl;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.CO2ViewModel;
import com.example.smartflowerpot.ViewModel.HumidityViewModel;
import com.example.smartflowerpot.ViewModel.LightLvlViewModel;
import com.example.smartflowerpot.ViewModel.PlantViewModel;
import com.example.smartflowerpot.ViewModel.SettingsViewModel;
import com.example.smartflowerpot.ViewModel.TemperatureViewModel;
import com.example.smartflowerpot.utils.LineGraph;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PlantFragment extends Fragment {
    private View view;
    private String deviceIdentifier;
    private TextView temperatureReading;
    private TextView co2Reading;
    private TextView humidityReading;
    //-----------Karlo-------------
    private TextView dateOfPlantation;
    private TextView daysSince;
    private TextView lightReading;
    private LightLvlViewModel lightLvlViewModel;
    //-----------------------------
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
        updatePlantInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plant, container, false);


        initViews();

        getViewModels();

        plantViewModel.getPlant().observe(getViewLifecycleOwner(), new Observer<Plant>() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onChanged(Plant plant) {
                System.out.println(plant);
                deviceIdentifier = plant.getEui();
                setDaysSincePlantation(plant);
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


        //-----------------------------------Karlo----------------------------------------------
        lightLvlViewModel.getLightLvlResponse().observe(getViewLifecycleOwner(), new Observer<LightLvl>() {
            @Override
            public void onChanged(LightLvl lightLvl) {
                lightReading.setText(String.valueOf(lightLvl.getLightLvl()));
            }
        });
        //--------------------------------------------------------------------------------------

        updatePlantInfo();

        return view;
    }

    //----------------------Karlo---------------------
    private void setDaysSincePlantation(@NonNull Plant plant) {
        //convert String date from the plant and set another format
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(plant.getDob());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);

        //get current time and estimate how long ago was the date of the plant
        long millis = System.currentTimeMillis();
        Date date1 = new Date(millis);

        long diffInMillies = Math.abs(date1.getTime() - date.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        dateOfPlantation.setText(formattedDate);
        daysSince.setText(String.valueOf(diff));
    }

    private void updatePlantInfo() {
        deviceIdentifier = requireArguments().getString("DeviceIdentifier");

        temperatureReading.setText(R.string.no_reading);
        co2Reading.setText(R.string.no_reading);
        humidityReading.setText(R.string.no_reading);
        //----------------------Karlo--------------------------------
        dateOfPlantation.setText("No reading");
        daysSince.setText("No reading");
        lightReading.setText("No reading");
        //-----------------------------------------------------------
        humidityViewModel.getWeekHumidityRequest(deviceIdentifier);
        co2ViewModel.getWeekCO2Request(deviceIdentifier);
        temperatureViewModel.getWeekTemperatureRequest(deviceIdentifier);
        //----------------------------------------------------------
        plantViewModel.getPlantInfo(deviceIdentifier);
        temperatureViewModel.getTemperatureRequest(deviceIdentifier);
        humidityViewModel.getHumidityRequest(deviceIdentifier);
        co2ViewModel.getCO2Request(deviceIdentifier);
        lightLvlViewModel.getLightLvlRequest(deviceIdentifier);
    }

    private void getViewModels() {
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        humidityViewModel = new ViewModelProvider(this).get(HumidityViewModel.class);
        co2ViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
        lightLvlViewModel = new ViewModelProvider(this).get(LightLvlViewModel.class);
    }

    private void initViews() {
        temperatureReading = view.findViewById(R.id.temperatureReading);
        co2Reading = view.findViewById(R.id.co2Reading);
        humidityReading = view.findViewById(R.id.humidityReading);

        co2Chart = view.findViewById(R.id.co2Chart);
        temperatureChart = view.findViewById(R.id.temperatureChart);
        humidityChart = view.findViewById(R.id.humidityChart);

        //----------------------Karlo--------------------------------
        dateOfPlantation = view.findViewById(R.id.dateOfPlantation);
        daysSince = view.findViewById(R.id.daysSince);
        lightReading = view.findViewById(R.id.lightReading);
        //-----------------------------------------------------------
    }

}