package com.oxymorus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Builder
@Data
@AllArgsConstructor
public class User {

    private final Long id;
    @With
    private String name;
    @With
    private String email;
    @With
    private long timestamp;

    public User(Long id) {
        this.id = id;
    }
}
