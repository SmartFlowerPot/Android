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

    public String getReading() {
        return cO2Level + " ppm";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getcO2Level() {
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

    public boolean isOpen_close_window() {
        return open_close_window;
    }

    public void setOpen_close_window(boolean open_close_window) {
        this.open_close_window = open_close_window;
    }

    @Override
    public String toString() {
        return "CO2{" +
                "id=" + id +
                ", cO2Level=" + cO2Level +
                ", timeStamp='" + timeStamp + '\'' +
                ", eui='" + eui + '\'' +
                ", open_close_window=" + open_close_window +
                '}';
    }

}
