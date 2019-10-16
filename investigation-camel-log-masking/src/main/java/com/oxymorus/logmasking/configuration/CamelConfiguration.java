package com.oxymorus.logmasking.configuration;

import com.oxymorus.logmasking.messaging.UserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.model.Constants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.DefaultMaskingFormatter;
import org.apache.camel.spi.MaskingFormatter;
import org.apache.camel.spi.Registry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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
    Registry maskingRegistry() {
        MaskingFormatter valueMaskingFormatter =
                new DefaultMaskingFormatter(
                        Collections.unmodifiableSet(new HashSet<>(Arrays.asList("name", "email"))),
                        true,
                        true,
                        true
                );
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        simpleRegistry.put(Constants.CUSTOM_LOG_MASK_REF, valueMaskingFormatter);
        return simpleRegistry;
    }

    @Bean
    RouteBuilder userRouteBuilder(UserProcessor userProcessor) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:clock?period=5000&delay=1000")
                        .setBody().header(Exchange.TIMER_COUNTER).convertBodyTo(Long.class)
                        .log("Generated userId ${body}")
                        .process(userProcessor)
                        .marshal().json(JsonLibrary.Jackson)
                        .log("Generated user ${body}");

            }
        };
    }
}
