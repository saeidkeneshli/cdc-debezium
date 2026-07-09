package io.github.saeidkeneshli.change.data.capture.debezium.model;

import io.github.saeidkeneshli.change.data.capture.debezium.model.enums.Operation;
import lombok.Data;

@Data
public class CdcEvent<T> {

    private T before;
    private T after;
    private Operation operation;
    private Source source;
    private Long timestamp;
}