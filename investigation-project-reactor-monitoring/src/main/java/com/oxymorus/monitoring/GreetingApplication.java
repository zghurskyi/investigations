package com.oxymorus.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class GreetingApplication {

    public static void main(String[] args) {
        Schedulers.enableMetrics();
        SpringApplication.run(GreetingApplication.class, args);
    }

}
