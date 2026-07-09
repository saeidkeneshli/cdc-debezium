package io.github.saeidkeneshli.change.data.capture.debezium.service;

import io.github.saeidkeneshli.change.data.capture.debezium.model.kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @Value("${kafka-publisher.topic}")
    private String topic;

    public void publish(KafkaMessage event) {
        kafkaTemplate.send(topic, event);
    }
}