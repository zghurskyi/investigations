package com.oxymorus.greetings.configuration;

import com.oxymorus.greetings.service.DefaultGreetingService;
import com.oxymorus.greetings.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfiguration {

    @Bean
    GreetingService greetingService() {
        return new DefaultGreetingService();
    }
}
