package com.example.scheduling_task.controller;

import com.example.scheduling_task.model.Data;
import com.example.scheduling_task.service.IMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {

    @Autowired
    private IMyService iMyService;

    @GetMapping("/data")
    public Data getIOTMeasure() throws InterruptedException {
        return iMyService.getData();
    }
}
