package com.example.scheduling_task.service;

import com.example.scheduling_task.model.Data;

public interface IMyService {
    double getTemperature();
    double getMoisture();
    Data getData() throws InterruptedException;
}
