package com.oxymorus.greeting.configuration;

import com.oxymorus.greeting.service.DefaultGreetingService;
import com.oxymorus.greeting.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfiguration {

    @Bean
    GreetingService greetingService() {
        return new DefaultGreetingService();
    }
}
