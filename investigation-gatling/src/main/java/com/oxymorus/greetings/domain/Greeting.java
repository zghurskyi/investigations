package com.oxymorus.greetings.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
public class Greeting {
    private String greeting;
}
