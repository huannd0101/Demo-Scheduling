package com.example.scheduling_task.service;

import com.example.scheduling_task.model.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@EnableScheduling
@ConditionalOnExpression("true")
public class MyService implements IMyService{

    @Override
    public double getTemperature() {
        int min = 10;
        int max = 40;
        return Math.random() * (max - min + 1) + min;
    }

    @Override
    public double getMoisture() {
        int min = 30;
        int max = 100;
        return Math.random() * (max - min + 1) + min;
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public Data getData() throws InterruptedException {
        Thread.sleep(5000);
        return new Data(getTemperature(), getMoisture());
    }

}
