package com.example.smartflowerpot.Model;

public class CO2 {
    private int id;
    private double cO2Level;
    private String timeStamp;
    private String eui;

    public CO2(double cO2Level, String timeStamp){
        this.timeStamp = timeStamp;
        this.cO2Level = cO2Level;
    }
    public CO2(int id, double cO2Level, String timeStamp, String eui) {
        this.id = id;
        this.cO2Level = cO2Level;
        this.timeStamp = timeStamp;
        this.eui = eui;
    }

    public CO2() {

    }

    public String getReading() {
        return cO2Level + " ppm";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getcO2level() {
        return cO2Level;
    }

    public void setcO2Level(double cO2Level) {
        this.cO2Level = cO2Level;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }



    @Override
    public String toString() {
        return "CO2{" +
                "id=" + id +
                ", cO2Level=" + cO2Level +
                ", timeStamp='" + timeStamp + '\'' +
                ", eui='" + eui + '\'' +
                '}';
    }

}
