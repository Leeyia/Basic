package com.dintech.api.bleep.exception;

import java.io.Serializable;

public class BleException implements Serializable {

    private int code;
    private String description;

    public BleException(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BleException{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
