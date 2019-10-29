package com.oxymorus.greeting.service;

import com.oxymorus.greeting.domain.Greeting;
import reactor.core.publisher.Mono;

public interface GreetingService {
    Mono<Long> createGreeting(String name, String greeting);

    Mono<Greeting> findGreeting(Long id);
}
