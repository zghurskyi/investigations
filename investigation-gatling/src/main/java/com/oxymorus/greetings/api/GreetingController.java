package com.oxymorus.greetings.api;

import com.oxymorus.greetings.api.model.GreetingsModel;
import com.oxymorus.greetings.domain.Person;
import com.oxymorus.greetings.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Mono<GreetingsModel> generateGreeting(@RequestBody Person person) {
        return service.generateGreeting(person)
                .map(greeting -> GreetingsModel.of(person, greeting));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Mono<GreetingsModel> getGreeting(Person person) {
        return service.findGreeting(person)
                .map(greeting -> GreetingsModel.of(person, greeting));
    }
}
