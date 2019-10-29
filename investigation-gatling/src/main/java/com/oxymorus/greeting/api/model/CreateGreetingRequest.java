package com.oxymorus.greeting.api.model;

import lombok.Value;

@Value
public class CreateGreetingRequest {
    private String name;
    private String greeting;
}
