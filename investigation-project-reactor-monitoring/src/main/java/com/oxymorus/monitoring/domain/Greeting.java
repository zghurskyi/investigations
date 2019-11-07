package com.oxymorus.monitoring.domain;

import lombok.Value;

@Value
public class Greeting {

    public static final Greeting DEFAULT = new Greeting("<undefined>", "<undefined>");

    private String name;
    private String greeting;
}
