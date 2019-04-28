package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

public class DisConnectedRequest extends Request {

    DisConnectedRequest(Type type, BluetoothDevice device) {
        super(type, device);
    }

    @Override
    DisConnectedRequest setBluetoothKit(final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @Override
    public DisConnectedRequest done(final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @Override
    public DisConnectedRequest fail(final FailCallback callback) {
        super.fail(callback);
        return this;
    }
}
