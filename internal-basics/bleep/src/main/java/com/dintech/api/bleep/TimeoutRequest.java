package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.IntRange;

import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

public class TimeoutRequest extends Request {

    private int timeout;

    TimeoutRequest(Type type, BluetoothDevice device) {
        super(type, device);
    }

    @Override
    TimeoutRequest setBluetoothKit(final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @Override
    TimeoutRequest done(SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @Override
    TimeoutRequest fail(FailCallback callback) {
        super.fail(callback);
        return this;
    }

    TimeoutRequest timeout(@IntRange(from = 0) int timeout) {
        this.timeout = timeout;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }
}
