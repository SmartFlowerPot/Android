package com.example.smartflowerpot.Model;

public class LightLvl {
    private double lightLvl;
    private String timestamp;

    public LightLvl(double lightLvl, String timestamp) {
        this.lightLvl = lightLvl;
        this.timestamp = timestamp;
    }

    public double getLightLvl() {
        return lightLvl;
    }

    public void setLightLvl(double lightLvl) {
        this.lightLvl = lightLvl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Ionut
    public String getReading(){
        return lightLvl + " lm";
    }

    @Override
    public String toString() {
        return "LightLvl{" +
                "lightLvl=" + lightLvl +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
