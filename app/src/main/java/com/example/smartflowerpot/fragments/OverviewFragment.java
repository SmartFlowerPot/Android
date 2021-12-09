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
import java.util.concurrent.TimeUnit;

public class OverviewFragment extends Fragment implements PlantsAdapter.OnListItemClickListener{
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

        if (isNetworkAvailable()){
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
        ((BaseActivity)getActivity()).setTopbarTitle("Your plants");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(String deviceIdentifier) {
        if (isNetworkAvailable()){
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
        setupAxis();
        setData(20);
      //  setupHumidityChart();
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



//    private void setupHumidityChart(){
//        List<Entry> values = new ArrayList<>();
//        Humidity humidity1 = new Humidity(200, "2021-12-07T10:22:03.329Z");
//        Humidity humidity2 = new Humidity(300, "2021-12-07T10:22:03.329Z");
//        Humidity humidity3 = new Humidity(400, "2021-12-08T10:22:03.329Z");
//        Humidity humidity4 = new Humidity(100, "2021-12-09T10:22:03.329Z");
//
//        values.add(new Entry(convertToFloatTimeStamp(humidity1.getTimestamp()), convertToFloat(humidity1.getHumidity())));
//        values.add(new Entry(convertToFloatTimeStamp(humidity2.getTimestamp()), convertToFloat(humidity2.getHumidity())));
//        values.add(new Entry(convertToFloatTimeStamp(humidity3.getTimestamp()), convertToFloat(humidity3.getHumidity())));
//        values.add(new Entry(convertToFloatTimeStamp(humidity4.getTimestamp()), convertToFloat(humidity4.getHumidity())));
//
//
//        LineDataSet linedataset = new LineDataSet(values, "Humidity");
//        //We add features to our chart
//        linedataset.setColor(R.color.purple_200);
//
//        linedataset.setCircleRadius(10f);
//        linedataset.setDrawFilled(true);
//        linedataset.setValueTextSize(20F);
//        linedataset.setFillColor(R.color.black);
//        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//
//        //We connect our data to the UI Screen
//        LineData data = new LineData(linedataset);
//
//        lineChart.setData(data);
//        lineChart.setBackgroundColor(getResources().getColor(R.color.white));
//        lineChart.animateXY(2000, 2000, Easing.EaseInCubic);
//
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new ValueFormatter() {
//
//            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH);
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//
//                long millis = TimeUnit.HOURS.toMillis((long) value);
//                return mFormat.format(new Date(millis));
//            }
//        });
//
//    }
    public static Float convertToFloat(double doubleValue) {
        return (float) doubleValue;
    }
    public static Float convertToFloatTimeStamp(String string) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(string);
            System.out.println("//////////////////////////" + date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (float) date.getDate();


    }

    private void setupAxis(){

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // set an alternative background color
        chart.setBackgroundColor(getResources().getColor(R.color.white));
        chart.setViewPortOffsets(0f, 0f, 0f, 0f);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        xAxis.setTextSize(10f);
        xAxis.setTextColor(getResources().getColor(R.color.black));
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new ValueFormatter() {

            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date(millis));
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setTextColor(getResources().getColor(R.color.black));
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(800f);
        leftAxis.setYOffset(-9f);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setData(int count){

        // now in hours
        long now = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());

        ArrayList<Entry> values = new ArrayList<>();

        // count = hours
        float to = now + count;

        // increment by 1 hour
        for (float x = now; x < to; x++) {

            float y = 50;
            values.add(new Entry(x, y)); // add one entry per hour
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1.5f);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);

        // create a data object with the data sets
        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        // set data
        chart.setData(data);
    }
}
