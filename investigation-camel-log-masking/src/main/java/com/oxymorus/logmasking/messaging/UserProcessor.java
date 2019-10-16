package com.oxymorus.logmasking.messaging;

import com.oxymorus.logmasking.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import java.time.Clock;

@RequiredArgsConstructor
public class UserProcessor implements Processor {

    private final Clock clock;

    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();

        Long userId = in.getBody(Long.class);

        User user = User.builder()
                .id(userId)
                .name(String.format("Name%s", userId))
                .email(String.format("user%s@example.com", userId))
                .timestamp(clock.millis())
                .build();

        in.setBody(user);
    }
}
