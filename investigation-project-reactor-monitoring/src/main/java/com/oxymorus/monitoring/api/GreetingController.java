package com.oxymorus.monitoring.api;

import com.oxymorus.monitoring.api.model.CreateGreetingRequest;
import com.oxymorus.monitoring.api.model.CreateGreetingResponse;
import com.oxymorus.monitoring.api.model.GetGreetingRequest;
import com.oxymorus.monitoring.api.model.GetGreetingResponse;
import com.oxymorus.monitoring.service.GreetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<CreateGreetingResponse> createGreeting(@RequestBody CreateGreetingRequest request) {
        return service.createGreeting(request.getName(), request.getGreeting())
                .metrics()
                .map(CreateGreetingResponse::new)
                .doOnSubscribe(subscription -> log.info("Create greeting '{}'", request));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<GetGreetingResponse> getGreeting(GetGreetingRequest request) {

        return service.findGreeting(request.getId())
                .metrics()
                .map(greeting -> new GetGreetingResponse(greeting.getName(), greeting.getGreeting()))
                .doOnSubscribe(subscription -> log.info("Get greeting by id '{}'", request.getId()));
    }
}
