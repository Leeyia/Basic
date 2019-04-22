package com.dintech.api.bleep;

import com.dintech.api.bleep.operation.Operation;
import com.dintech.api.bleep.operation.SystemOperation;

public class Configurations {

    private Builder builder;

    private Configurations(Builder builder) {
        this.builder = builder;
    }

    public String getServiceUuid() {
        return Preconditions.checkNotNull(builder.serviceUuid);
    }

    public String getCharacteristicUuid() {
        return Preconditions.checkNotNull(builder.characteristicUuid);
    }

    public Operation getOperation() {
        return Preconditions.checkNotNull(builder.operation);
    }

    public boolean isSplit() {
        return Preconditions.checkNotNull(builder.split);
    }

    public static class Builder {
        private String serviceUuid = UUID.SERVICE_UUID;
        private String characteristicUuid = UUID.CHARACTER_UUID;
        private Operation operation = new SystemOperation();
        private boolean split;

        public Builder serviceUuid(String serviceUuid) {
            this.serviceUuid = serviceUuid;
            return this;
        }

        public Builder characterUuid(String characteristicUuid) {
            this.characteristicUuid = characteristicUuid;
            return this;
        }

        public Builder operation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public Builder setSplit(boolean split) {
            this.split = split;
            return this;
        }

        public Configurations build() {
            return new Configurations(this);
        }
    }

    public static final class UUID {
        public final static String SERVICE_UUID = "0000fff0-0000-1000-8000-00805f9b34fb";
        public final static String CHARACTER_UUID = "0000fff3-0000-1000-8000-00805f9b34fb";
    }
}
