package com.example.smartflowerpot.Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Temperature implements Serializable {
    private double temperature;
    private String timeStamp;

    public Temperature( String timeStamp, double temperature) {
        this.temperature = temperature;
        this.timeStamp = timeStamp;
    }

    public Temperature() {
    }

    private double convertToFahrenheit(){
        return temperature*(9/5) + 32;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "temperature=" + temperature +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public String getCelsiusReading(){
        return temperature + " °C";
    }

    public String getFahrenheitReading(){
        return convertToFahrenheit() + " °F";
    }
}
