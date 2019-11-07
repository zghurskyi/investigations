package com.oxymorus.monitoring.configuration;

import com.oxymorus.monitoring.service.DefaultGreetingService;
import com.oxymorus.monitoring.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfiguration {

    @Bean
    public GreetingService greetingService() {
        return new DefaultGreetingService();
    }
}
