package com.example.smartflowerpot.utils;

import android.content.res.Resources;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.smartflowerpot.Model.CO2;
import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.Model.Temperature;
import com.example.smartflowerpot.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LineGraph {

    private LineChart chart;
    private Resources resources;
    private List values;
    YAxis leftAxis;

    public LineGraph(LineChart chart, Resources resources) {
        this.chart = chart;
        this.resources = resources;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setupHumidityGraph(ArrayList<Humidity> dataToInsert) {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimestamp()), convertToFloat(dataToInsert.get(i).getHumidity())));
        }
        values.sort(Comparator.comparing(Entry :: getX));
        this.values = values;
        setup();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setYOffset(-9f);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setupCO2Graph(ArrayList<CO2> dataToInsert) {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimeStamp()), convertToFloat(dataToInsert.get(i).getcO2level())));
        }
        values.sort(Comparator.comparing(Entry :: getX));
        this.values = values;
        setup();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(2400f);
        leftAxis.setYOffset(-9f);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setupTemperatureGraph(ArrayList<Temperature> dataToInsert) {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimeStamp()), convertToFloat(dataToInsert.get(i).getTemperature())));
        }
        values.sort(Comparator.comparing(Entry :: getX));
        this.values = values;
        setup();
        leftAxis.setAxisMinimum(-30f);
        leftAxis.setAxisMaximum(60f);
        leftAxis.setYOffset(-9f);
    }


    private void setup() {
        LineDataSet linedataset = new LineDataSet(values, "");
        linedataset.setColor(R.color.black);
        linedataset.setDrawFilled(true);
        linedataset.setFillColor(R.color.black);
        linedataset.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        linedataset.setDrawCircles(true);
        linedataset.setDrawValues(false);
        LineData data = new LineData(linedataset);
        chart.setData(data);
        chart.setBackgroundColor(resources.getColor(R.color.white));
        chart.animateXY(1000, 1000, Easing.EaseInCubic);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(false);
        chart.getDescription().setEnabled(true);
        Description description = new Description();
        description.setText("Last Week");
        chart.setDescription(description);
        Legend l = chart.getLegend();
        l.setEnabled(false);
        XAxis xAxis = chart.getXAxis();
    //    xAxis.setGranularity(1f);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(resources.getColor(R.color.white));
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setCenterAxisLabels(true);
       // xAxis.mAxisRange = 3000000000F;

        xAxis.setGranularityEnabled(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(R.color.black);
        leftAxis.setGranularityEnabled(true);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    public static Float convertToFloat(double doubleValue) {
        return (float) doubleValue;
    }

    public static Float convertToFloatTimeStamp(String string) {
        Date date = null;
        if (string.length() > 21){
            string = string.substring(0, 21);
        }
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (float) date.getTime();

    }

}

