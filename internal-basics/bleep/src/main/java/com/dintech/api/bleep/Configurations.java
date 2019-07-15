package com.dintech.api.bleep;

import android.app.Application;

import com.dintech.api.bleep.internal.Operation;

import static com.dintech.api.bleep.Preconditions.checkNotNull;

public class Configurations {

    private Builder builder;

    private Configurations(Builder builder) {
        this.builder = builder;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getServiceUuid() {
        return checkNotNull(builder.serviceUuid);
    }

    public String getCharacterUuid() {
        return checkNotNull(builder.characterUuid);
    }

    public Application getApplication() {
        return checkNotNull(builder.application);
    }

    public Operation getTrigger() {
        return checkNotNull(builder.trigger);
    }

    public boolean isSplit() {
        return checkNotNull(builder.split);
    }

    public final static class Builder {
        private String serviceUuid;
        private String characterUuid;
        private Application application;
        private Operation trigger;
        private boolean split;

        public Builder serviceUuid(String serviceUuid) {
            this.serviceUuid = serviceUuid;
            return this;
        }

        public Builder characterUuid(String characterUuid) {
            this.characterUuid = characterUuid;
            return this;
        }

        public Builder application(Application application) {
            this.application = application;
            return this;
        }

        public Builder trigger(Operation trigger) {
            this.trigger = trigger;
            return this;
        }

        public Builder split(boolean split) {
            this.split = split;
            return this;
        }

        public Configurations newInstance() {
            return new Configurations(this);
        }
    }
}
