package com.example.smartflowerpot.Model;

public class CO2 {
    private double co2;
    private String timeStamp;

    public CO2(double co2, String timeStamp) {
        this.co2 = co2;
        this.timeStamp = timeStamp;
    }

    public CO2() {

    }

    @Override
    public String toString() {
        return "CO2{" +
                "co2=" + co2 +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
