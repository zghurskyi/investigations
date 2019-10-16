package com.oxymorus.logmasking.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Long id;

    @ToString.Exclude
    private String name;

    @ToString.Exclude
    private String email;

    private long timestamp;
}
