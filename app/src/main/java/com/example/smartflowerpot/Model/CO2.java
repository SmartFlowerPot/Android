package com.example.smartflowerpot.Model;

public class CO2 {
    private int id;
    private double cO2Level;
    private String timeStamp;
    private String eui;
    private boolean open_close_window;

    public CO2(int id, double cO2Level, String timeStamp, String eui, boolean open_close_window) {
        this.id = id;
        this.cO2Level = cO2Level;
        this.timeStamp = timeStamp;
        this.eui = eui;
        this.open_close_window = open_close_window;
    }

    public CO2() {

    }

    public String getReading(){
        return cO2Level + " ppm";
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

    public String getTimeStamp() {
        return timeStamp;
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

    public void setcO2level(double cO2level) {
        this.cO2Level = cO2level;
    }

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
