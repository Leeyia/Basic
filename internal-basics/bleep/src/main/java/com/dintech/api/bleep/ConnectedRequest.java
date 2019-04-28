package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.IntRange;

import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

public class ConnectedRequest extends TimeoutRequest {

    @IntRange(from = 0, to = 10)
    private int retries = 0;

    @IntRange(from = 0)
    private int delay = 0;

    ConnectedRequest(final Type type, final BluetoothDevice device) {
        super(type, device);
    }

    @Override
    ConnectedRequest setBluetoothKit(final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @Override
    public ConnectedRequest done(final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @Override
    public ConnectedRequest fail(final FailCallback callback) {
        super.fail(callback);
        return this;
    }

    @Override
    public ConnectedRequest timeout(int timeout) {
        super.timeout(timeout);
        return this;
    }

    @Override
    protected Request discall(DisConnectionCallback callback) {
        super.discall(callback);
        return this;
    }

    public ConnectedRequest retry(@IntRange(from = 0, to = 10) final int count) {
        this.retries = count;
        this.delay = 0;
        return this;
    }

    public ConnectedRequest retry(@IntRange(from = 0) final int count, @IntRange(from = 0) final int delay) {
        this.retries = count;
        this.delay = delay;
        return this;
    }

    public int getRetries() {
        return retries;
    }

    @IntRange(from = 0)
    int getRetryDelay() {
        return delay;
    }

    boolean canRetry() {
        if (retries > 0) {
            retries -= 1;
            return true;
        }
        return false;
    }
}
