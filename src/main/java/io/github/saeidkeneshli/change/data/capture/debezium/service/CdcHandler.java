package io.github.saeidkeneshli.change.data.capture.debezium.service;

import io.github.saeidkeneshli.change.data.capture.debezium.model.CdcEvent;

public interface CdcHandler<T> {

    void handle(CdcEvent<T> event);
}