package com.oxymorus.greeting.api.model;

import lombok.Value;

@Value
public class GetGreetingResponse {
    private String name;
    private String greeting;
}
