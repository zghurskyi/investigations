package com.oxymorus.configuration;

import com.oxymorus.configuration.properties.CamelKafkaProducerProperties;
import com.oxymorus.listener.CamelKafkaRoutesInitializingListener;
import com.oxymorus.messaging.UserProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.reactive.streams.util.UnwrapStreamProcessor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class CamelConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    @ConfigurationProperties("camel.kafka")
    public CamelKafkaProducerProperties camelKafkaProducerProperties() {
        return new CamelKafkaProducerProperties();
    }

    @Bean
    public CamelKafkaRoutesInitializingListener routesInitializingListener(CamelKafkaProducerProperties camelKafkaProducerProperties, CamelContext camelContext) {
        return new CamelKafkaRoutesInitializingListener(camelKafkaProducerProperties, camelContext);
    }

    @Bean
    public UserProcessor userProcessor(Clock clock) {
        return new UserProcessor(clock);
    }

    @Bean
    public RouteBuilder userRouteBuilder(UserProcessor userProcessor) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:clock?period=5000&delay=1000")
                        .setBody().header(Exchange.TIMER_COUNTER).convertBodyTo(Long.class)
                        .log(LoggingLevel.INFO, "Generated userId ${body}")
                        .bean(userProcessor, "process")
                        .process(new UnwrapStreamProcessor())
                        .marshal().json(JsonLibrary.Jackson)

                        .multicast()
                        .to("direct:producer-test0", "direct:producer-test1", "direct:producer-test2");
            }
        };
    }
}
