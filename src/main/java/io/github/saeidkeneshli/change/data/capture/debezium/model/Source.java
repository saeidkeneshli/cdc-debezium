package io.github.saeidkeneshli.change.data.capture.debezium.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    private String db;
    private String table;
    private String name;
    private String file;
    private Long pos;
}