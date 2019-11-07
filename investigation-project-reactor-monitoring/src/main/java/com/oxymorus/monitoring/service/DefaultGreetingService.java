package com.oxymorus.monitoring.service;

import com.oxymorus.monitoring.domain.Greeting;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultGreetingService implements GreetingService {

    private static final Map<Long, Greeting> GREETINGS_STORAGE = new ConcurrentHashMap<>();

    @Override
    public Mono<Long> createGreeting(String name, String greeting) {
        return Mono.fromCallable(Generator::next)
                .map(id -> {
                    GREETINGS_STORAGE.put(id, new Greeting(name, greeting));
                    return id;
                });
    }

    @Override
    public Mono<Greeting> findGreeting(Long id) {
        return Mono.fromCallable(() -> GREETINGS_STORAGE.getOrDefault(id, Greeting.DEFAULT));
    }

    @UtilityClass
    private static class Generator {
        private static final AtomicLong id = new AtomicLong(0);

        private static long next() {
            return id.incrementAndGet();
        }
    }
}
