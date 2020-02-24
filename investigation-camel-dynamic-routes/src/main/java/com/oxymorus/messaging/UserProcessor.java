package com.oxymorus.messaging;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import com.oxymorus.domain.User;
import java.time.Clock;

@RequiredArgsConstructor
public class UserProcessor {

    private final Clock clock;

    public Publisher<User> process(Long userId) {
        return Flux.just(userId)
                .map(User::new)
                .map(user -> user.withName(String.format("Name%s", userId)))
                .map(user -> user.withEmail(String.format("user%s@example.com", userId)))
                .map(user -> user.withTimestamp(clock.millis()));
    }
}
