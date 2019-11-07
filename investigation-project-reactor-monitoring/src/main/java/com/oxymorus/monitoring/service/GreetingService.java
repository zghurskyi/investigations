package com.oxymorus.monitoring.service;

import com.oxymorus.monitoring.domain.Greeting;
import reactor.core.publisher.Mono;

public interface GreetingService {

    Mono<Long> createGreeting(String name, String greeting);

    Mono<Greeting> findGreeting(Long id);
}
