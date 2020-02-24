package com.oxymorus.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import static com.oxymorus.configuration.properties.CamelKafkaProducerProperties.ProducerProperties;

@Slf4j
@RequiredArgsConstructor
public class CamelKafkaProducerRoutesBuilder extends RouteBuilder {

    private final String key;
    private final ProducerProperties producerProperties;

    public CamelKafkaProducerRoutesBuilder(CamelContext context, String key, ProducerProperties producerProperties) {
        super(context);
        this.key = key;
        this.producerProperties = producerProperties;
    }

    @Override
    public void configure() throws Exception {
        from("direct:producer-" + key)
                .log(LoggingLevel.INFO, "Sending to Kafka: ${body}")
                .to(producerProperties.getUri())
                .log("Successfully sent ${body}");
    }
}
