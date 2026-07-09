package io.github.saeidkeneshli.change.data.capture.debezium.service;

import io.github.saeidkeneshli.change.data.capture.debezium.mapper.IncomingMessageMapper;
import io.github.saeidkeneshli.change.data.capture.debezium.model.CdcEvent;
import io.github.saeidkeneshli.change.data.capture.debezium.model.IncomingMessage;
import io.github.saeidkeneshli.change.data.capture.debezium.model.kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomingMessageHandler implements CdcHandler<IncomingMessage> {

    private final IncomingMessageMapper incomingMessageMapper;
    private final KafkaMessagePublisher kafkaMessagePublisher;

    @Override
    public void handle(CdcEvent<IncomingMessage> event) {

        switch (event.getOperation()) {
            case CREATE -> {
                KafkaMessage kafkaMessage = incomingMessageMapper.toKafkaMessage(event.getAfter());
                kafkaMessagePublisher.publish(kafkaMessage);
            }
//            case UPDATE -> System.out.println("Updated status: "
//                    + event.getAfter().getStatus());
//            case DELETE -> System.out.println("Deleted id="
//                    + event.getBefore().getId());
            default -> {
            }
        }

    }
}