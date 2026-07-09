package io.github.saeidkeneshli.change.data.capture.debezium.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload<T> {

    private T before;
    private T after;
    private Source source;
    private String op;
    private Long ts_ms;
}