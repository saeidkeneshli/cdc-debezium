package io.github.saeidkeneshli.change.data.capture.debezium.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.saeidkeneshli.change.data.capture.debezium.model.CdcEvent;
import io.github.saeidkeneshli.change.data.capture.debezium.model.enums.Operation;
import io.github.saeidkeneshli.change.data.capture.debezium.model.Source;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DebeziumMapper {
    private final ObjectMapper objectMapper;
    private final Logger debeziumCdcLogger;

    public <T> CdcEvent<T> toEvent(String json, Class<T> clazz) {

        try {
            JsonNode root = objectMapper.readTree(json);

            // Supports both:
            // {schema,payload}
            // and
            // {before,after,...}
            JsonNode payload = root.has("payload") ? root.get("payload") : root;

            CdcEvent<T> event = new CdcEvent<>();

            event.setOperation(Operation.fromCode(payload.get("op").asText()));

            if (payload.hasNonNull("ts_ms")) {
                event.setTimestamp(payload.get("ts_ms").asLong());
            }

            if (payload.has("source")) {
                event.setSource(objectMapper.treeToValue(payload.get("source"), Source.class));
            }

            if (payload.hasNonNull("before")) {
                event.setBefore(objectMapper.treeToValue(payload.get("before"), clazz));
            }

            if (payload.hasNonNull("after")) {
                event.setAfter(objectMapper.treeToValue(payload.get("after"),clazz));
            }

            return event;

        } catch (Exception e) {
            debeziumCdcLogger.error("Cannot parse Debezium event");
            throw new RuntimeException("Cannot parse Debezium event", e);
        }
    }
}