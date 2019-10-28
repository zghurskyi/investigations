package com.oxymorus.greetings.api.model;

import com.oxymorus.greetings.domain.Greeting;
import com.oxymorus.greetings.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class GreetingsModel {
    private Person person;
    private Greeting greeting;
}
