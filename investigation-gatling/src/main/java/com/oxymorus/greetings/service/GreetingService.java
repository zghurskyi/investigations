package com.oxymorus.greetings.service;

import com.oxymorus.greetings.domain.Greeting;
import com.oxymorus.greetings.domain.Person;
import reactor.core.publisher.Mono;

public interface GreetingService {
    Mono<Greeting> generateGreeting(Person person);

    Mono<Greeting> findGreeting(Person person);
}
