package com.example.scheduling_task.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableAsync
@Configuration
@Component
@EnableScheduling
@ConditionalOnExpression("true")
public class Demo {

//    @Async
//    @Scheduled(fixedDelay = 5000)
//    @Scheduled(fixedRate = 1000)
//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
//    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "0 * * ? * *")
    public void Exc() {
        System.out.println("Exc - " + System.currentTimeMillis() / 1000);
    }

}
