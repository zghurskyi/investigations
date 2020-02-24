package com.oxymorus.configuration.properties;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CamelKafkaProducerProperties {

    @NotEmpty
    private Map<String, @Valid ProducerProperties> producers;

    @Data
    public static class ProducerProperties {
        @NotNull
        private String brokers;
        @NotNull
        private String topic;
        @NotNull
        private String serializerClass = "org.apache.kafka.common.serialization.StringSerializer";
        @NotNull
        private String keySerializerClass = "org.apache.kafka.common.serialization.StringSerializer";

        public String getUri() {
            return "kafka:start" +
                    "?brokers=" + brokers +
                    "&topic=" + topic +
                    "&serializerClass=" + serializerClass +
                    "&keySerializerClass=" + keySerializerClass;
        }
    }
}
