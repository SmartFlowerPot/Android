package com.example.smartflowerpot.Model;

import java.sql.Timestamp;

public class Temperature {
    private double temperature;
    private Timestamp timeStamp;

    public Temperature(double temperature, Timestamp timeStamp) {
        this.temperature = temperature;
        this.timeStamp = timeStamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "temperature=" + temperature +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
