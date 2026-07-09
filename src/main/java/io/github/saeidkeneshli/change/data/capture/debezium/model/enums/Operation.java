package io.github.saeidkeneshli.change.data.capture.debezium.model.enums;

public enum Operation {

    CREATE("c"), UPDATE("u"), DELETE("d"), READ("r");
    private final String code;

    Operation(String code) {
        this.code = code;
    }

    public static Operation fromCode(String code) {
        for (Operation op : values()) {
            if (op.code.equals(code)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operation: " + code);
    }
}