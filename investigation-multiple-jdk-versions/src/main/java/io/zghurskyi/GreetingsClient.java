package io.zghurskyi;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    @Data
    @AllArgsConstructor
    class CreateGreetingRequest {
        private String name;
        private String greeting;
    }

    @Data
    @AllArgsConstructor
    class GetGreetingRequest {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    class CreateGreetingResponse {
        private long id;
    }

    @Data
    @AllArgsConstructor
    class GetGreetingResponse {
        private String name;
        private String greeting;
    }
}
