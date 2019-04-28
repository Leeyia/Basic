package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.ReceiveCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

public class NotificationRequest extends Request {

    NotificationRequest(final Type type, final BluetoothDevice device) {
        super(type, device);
    }

    @Override
    NotificationRequest setBluetoothKit(final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @Override
    public NotificationRequest done(final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @Override
    public NotificationRequest fail(final FailCallback callback) {
        super.fail(callback);
        return this;
    }

    @Override
    public NotificationRequest receive(ReceiveCallback callback) {
        super.receive(callback);
        return this;
    }
}
