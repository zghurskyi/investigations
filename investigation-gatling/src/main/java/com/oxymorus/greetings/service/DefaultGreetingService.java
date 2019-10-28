package com.oxymorus.greetings.service;

import com.oxymorus.greetings.domain.Greeting;
import com.oxymorus.greetings.domain.Person;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultGreetingService implements GreetingService {

    private static final Greeting DEFAULT_GREETING = Greeting.of("Привіт! Як справи?");

    private static final List<Greeting> GREETINGS = Arrays.asList(
            Greeting.of("Hola. ¿Cómo está?"), Greeting.of("Ciao! Come stai?"),
            Greeting.of("Hi! How are you!"), DEFAULT_GREETING);

    private static final Map<Person, Greeting> BINDINGS = new ConcurrentHashMap<>();

    @Override
    public Mono<Greeting> generateGreeting(Person person) {
        Collections.shuffle(GREETINGS);
        Greeting greeting = GREETINGS.get(0);
        BINDINGS.put(person, greeting);
        return Mono.just(greeting);
    }

    @Override
    public Mono<Greeting> findGreeting(Person person) {
        return Mono.just(BINDINGS.getOrDefault(person, DEFAULT_GREETING));
    }
}
