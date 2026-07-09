package io.github.saeidkeneshli.change.data.capture.debezium.model.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaMessage implements Serializable {
    private Date creationDate;
    private String messageId;
    private String messageText;
    private Long ttl;
}
