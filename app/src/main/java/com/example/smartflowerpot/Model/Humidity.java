package com.example.smartflowerpot.Model;

import java.io.Serializable;

//  Ionut

public class Humidity implements Serializable {
    private double humidity;
    private String timestamp;

    public Humidity(double humidity, String timestamp) {
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public Humidity() {
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "humidity=" + humidity +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
