package com.oxymorus.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

@Builder
@Data
@AllArgsConstructor
public class User {

    private final Long id;

    @Wither
    private String name;

    @Wither
    private String email;

    @Wither
    private long timestamp;

    public User(Long id) {
        this.id = id;
    }
}
