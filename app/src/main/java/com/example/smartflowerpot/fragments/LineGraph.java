package com.example.smartflowerpot.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import com.example.smartflowerpot.Model.Humidity;
import com.example.smartflowerpot.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
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

    public LineGraph(LineChart chart, Resources resources, ArrayList<Humidity> dataToInsert) {

        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataToInsert.size(); i++) {
            values.add(new Entry(convertToFloatTimeStamp(dataToInsert.get(i).getTimestamp()), convertToFloat(dataToInsert.get(i).getHumidity())));
        }


        LineDataSet linedataset = new LineDataSet(values, "Humidity");


        //We add features to our chart
        linedataset.setColor(R.color.black);
        linedataset.setDrawFilled(true);
        linedataset.setFillColor(R.color.black);
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        linedataset.setDrawCircles(false);
        linedataset.setDrawValues(false);
        LineData data = new LineData(linedataset);

        chart.setData(data);
        chart.setBackgroundColor(resources.getColor(R.color.white));
        chart.animateXY(2000, 2000, Easing.EaseInCubic);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);
        chart.getDescription().setEnabled(false);
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
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(R.color.black);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(800f);
        leftAxis.setYOffset(-9f);
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

