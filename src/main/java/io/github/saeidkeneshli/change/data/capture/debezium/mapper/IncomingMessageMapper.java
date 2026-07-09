package io.github.saeidkeneshli.change.data.capture.debezium.mapper;

import io.github.saeidkeneshli.change.data.capture.debezium.model.IncomingMessage;
import io.github.saeidkeneshli.change.data.capture.debezium.model.kafka.KafkaMessage;
import io.github.saeidkeneshli.change.data.capture.debezium.util.PhoneNumberUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class IncomingMessageMapper {

    public KafkaMessage toKafkaMessage(IncomingMessage incomingMessage) {

        return KafkaMessage.builder()
                .messageId(UUID.randomUUID().toString())
                .creationDate(new Date())
                .messageText(incomingMessage.getMessage())
                .ttl(5L)
                .build();
    }
}