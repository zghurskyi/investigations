package com.oxymorus.reactive.configuration;

import com.oxymorus.reactive.messaging.UserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.reactive.streams.util.UnwrapStreamProcessor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class CamelConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    UserProcessor userProcessor(Clock clock) {
        return new UserProcessor(clock);
    }

    @Bean
    RouteBuilder userRouteBuilder(UserProcessor userProcessor) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:clock?period=5000&delay=1000")
                        .setBody().header(Exchange.TIMER_COUNTER).convertBodyTo(Long.class)
                        .log("Generated userId ${body}")
                        .bean(userProcessor, "process")
                        .process(new UnwrapStreamProcessor())
                        .marshal().json(JsonLibrary.Jackson)
                        .log("Generated user ${body}");

            }
        };
    }
}
