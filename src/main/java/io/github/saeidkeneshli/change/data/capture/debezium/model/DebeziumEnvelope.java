package io.github.saeidkeneshli.change.data.capture.debezium.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumEnvelope<T> {
    private Payload<T> payload;
}