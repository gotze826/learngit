package com.bjtu.redis;

public class CounterSpec {
    private String counter_name;
    private String counter_index;
    private String type;
    private String key_fields;
    private String field;
    private String value_fields;

    public String getCounterName() {
        return counter_name;
    }

    public void setCounterName(String counter_name) {
        this.counter_name = counter_name;
    }

    public String getCounterIndex() {
        return counter_index;
    }

    public void setCounterIndex(String counter_index) {
        this.counter_index = counter_index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey_Fields() {
        return key_fields;
    }

    public void setKey_Fields(String key_fields) {
        this.key_fields = key_fields;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue_Fields() {
        return value_fields;
    }

    public void setValue_fields(String value_fields) {
        this.value_fields = value_fields;
    }
}
