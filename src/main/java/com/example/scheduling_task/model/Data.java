package com.example.scheduling_task.model;

public class Data {
    private double temperature;
    private double moisture;

    public Data() {
    }

    public Data(double temperature, double moisture) {
        this.temperature = temperature;
        this.moisture = moisture;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(float moisture) {
        this.moisture = moisture;
    }
}
