package com.oxymorus.listener;

import com.oxymorus.configuration.properties.CamelKafkaProducerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CamelKafkaRoutesInitializingListener {

    private final CamelKafkaProducerProperties producerProperties;
    private final CamelContext camelContext;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeRoutes() {
        try {
            log.info("Building Kafka producer routes: " + producerProperties);
            List<CamelKafkaProducerRoutesBuilder> routesBuilders = producerRoutesBuilder();
            for (CamelKafkaProducerRoutesBuilder routesBuilder : routesBuilders) {
                camelContext.addRoutes(routesBuilder);
            }
        } catch (Exception exception) {
            log.error("Failed to build dynamic routes: " + producerProperties, exception);
        }
    }

    private List<CamelKafkaProducerRoutesBuilder> producerRoutesBuilder() {
        return producerProperties.getProducers().entrySet().stream()
                .map(e -> new CamelKafkaProducerRoutesBuilder(camelContext, e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
