package com.oxymorus.greeting.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetGreetingRequest {
    private Long id;
}
