package io.zghurskyi;

import io.zghurskyi.model.CreateGreetingRequest;
import io.zghurskyi.model.CreateGreetingResponse;
import io.zghurskyi.model.GetGreetingRequest;
import io.zghurskyi.model.GetGreetingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "greetings-client", path = "/")
public interface GreetingsClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CreateGreetingResponse createGreeting(@RequestBody CreateGreetingRequest request);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    GetGreetingResponse getGreeting(GetGreetingRequest request);
}
