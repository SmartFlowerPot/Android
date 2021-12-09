package com.example.smartflowerpot.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartflowerpot.Activity.BaseActivity;
import com.example.smartflowerpot.Adapters.PlantsAdapter;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.ViewModel.AccountViewModel;
import com.example.smartflowerpot.ViewModel.PlantViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OverviewFragment extends Fragment implements PlantsAdapter.OnListItemClickListener {
    private View view;
    private RecyclerView recycledViewPlants;
    private PlantsAdapter plantsAdapter;
    private AccountViewModel accountViewModel;
    private PlantViewModel plantViewModel;

    LineChart chart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overview, container, false);

        initViews();

        getViewModels();

        plantsAdapter = new PlantsAdapter(new ArrayList<>(), this);
        recycledViewPlants.setAdapter(plantsAdapter);

        if (isNetworkAvailable()) {
            plantViewModel.getPlantsFromAPI("karlo");

            plantViewModel.getPlantsResponseFromAPI().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
                @Override
                public void onChanged(List<Plant> plants) {
                    plantsAdapter.setmPlants(plants);
                    recycledViewPlants.setAdapter(plantsAdapter);
                }
            });
        } else {
            System.out.println("Gotten plants from db instead");
            plantViewModel.getPlantsFromDb();

            plantViewModel.getPlantsResponseFromDb().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
                @Override
                public void onChanged(List<Plant> plants) {
                    plantsAdapter.setmPlants(plants);
                    recycledViewPlants.setAdapter(plantsAdapter);
                }
            });
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity) getActivity()).setTopbarTitle("Your plants");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(String deviceIdentifier) {
        if (isNetworkAvailable()) {
            Bundle bundle = new Bundle();
            bundle.putString("DeviceIdentifier", deviceIdentifier);
            Navigation.findNavController(view).navigate(R.id.action_overviewFragment_to_plant, bundle);
        } else {
            Toast.makeText(getContext(), "Connect to internet in order to get more info", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        chart = view.findViewById(R.id.lineChart);
        recycledViewPlants = view.findViewById(R.id.recycledViewPlants);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycledViewPlants.setLayoutManager(layoutManager);
        ArrayList<Humidity> humidityTest = new ArrayList();
        Humidity humidity1 = new Humidity(200, "2021-12-06T01:22:03.329Z");
        Humidity humidity2 = new Humidity(300, "2021-12-07T10:22:03.329Z");
        Humidity humidity3 = new Humidity(400, "2021-12-08T22:22:03.329Z");
        Humidity humidity4 = new Humidity(100, "2021-12-09T14:22:03.329Z");
        humidityTest.add(humidity1);
        humidityTest.add(humidity2);
        humidityTest.add(humidity3);
        humidityTest.add(humidity4);
        LineGraph lineGraph = new LineGraph(chart, getResources(), humidityTest );
    }

    private void getViewModels() {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
