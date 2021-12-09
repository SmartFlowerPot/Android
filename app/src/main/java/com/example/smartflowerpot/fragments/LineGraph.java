package com.example.smartflowerpot.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;

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

    public void setupHumidityGraph(ArrayList<Humidity> dataToInsert) {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimestamp()), convertToFloat(dataToInsert.get(i).getHumidity())));
        }
        this.values = values;
        setup();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setYOffset(-9f);
    }

    public void setupCO2Graph(ArrayList<CO2> dataToInsert) {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimeStamp()), convertToFloat(dataToInsert.get(i).getcO2level())));
        }
        this.values = values;
        setup();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(2000f);
        leftAxis.setYOffset(-9f);
    }

    public void setupTemperatureGraph(ArrayList<Temperature> dataToInsert) {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimeStamp()), convertToFloat(dataToInsert.get(i).getTemperature())));
        }
        this.values = values;
        setup();
        leftAxis.setAxisMinimum(-50f);
        leftAxis.setAxisMaximum(50f);
        leftAxis.setYOffset(-9f);
    }


    private void setup() {
        LineDataSet linedataset = new LineDataSet(values, "");
        linedataset.setColor(R.color.black);
        linedataset.setDrawFilled(true);
        linedataset.setFillColor(R.color.black);
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        linedataset.setDrawCircles(false);
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
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(resources.getColor(R.color.white));
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setGranularityEnabled(true);
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
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(string);
            System.out.println("//////////////////////////" + date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (float) date.getTime();

    }

}

