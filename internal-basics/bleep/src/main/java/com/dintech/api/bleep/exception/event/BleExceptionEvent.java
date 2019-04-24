package com.dintech.api.bleep.exception.event;

import com.dintech.api.bleep.exception.BleException;
import com.dintech.api.bleep.exception.ConnectedException;

public abstract class BleExceptionEvent {

    public BleExceptionEvent onEvent(BleException exception) {
        if (exception != null) {
            if (exception instanceof ConnectedException) {
                onEventConnected((ConnectedException) exception);
            }
        }
        return this;
    }

    abstract void onEventConnected(ConnectedException e);
}
