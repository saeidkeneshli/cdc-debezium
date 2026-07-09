package io.github.saeidkeneshli.change.data.capture.debezium.service;

import io.github.saeidkeneshli.change.data.capture.debezium.mapper.DebeziumMapper;
import io.github.saeidkeneshli.change.data.capture.debezium.model.CdcEvent;
import io.github.saeidkeneshli.change.data.capture.debezium.model.IncomingMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomingMessageConsumer {

    private final DebeziumMapper mapper;
    private final Logger debeziumCdcLogger;
    private final IncomingMessageHandler incomingMessageHandler;

    @KafkaListener(topics = "dbserver1.testdb.incoming_message", groupId = "cdc-group")
    public void consume(String message) {

        try {
            CdcEvent<IncomingMessage> event = mapper.toEvent(message, IncomingMessage.class);
            incomingMessageHandler.handle(event);
        } catch (Exception ex) {
            debeziumCdcLogger.error("failed to consume message: " + message);
        }
    }
}