package io.github.saeidkeneshli.change.data.capture.debezium.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncomingMessage {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("source_number")
    private String sourceNumber;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message_status")
    private Short messageStatus;

    @JsonProperty("date")
    private Long date;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("creation_date")
    private LocalDateTime creationDate;

    @JsonProperty("DCS")
    private Integer dcs;

    @JsonProperty("dest_port")
    private Integer destPort;

    @JsonProperty("source_port")
    private Integer sourcePort;

    @JsonProperty("IsRead")
    private Boolean isRead;

    @JsonProperty("UserName")
    private String userName;
}