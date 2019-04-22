package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.DisConnectionCallback;
import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

public class ConnectRequest extends Request {

    ConnectRequest(final Type type, final BluetoothDevice device) {
        super(type, device);
    }


    @Override
    ConnectRequest setBluetoothKit(final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }


    @Override
    public ConnectRequest done(final SuccessCallback callback) {
        super.done(callback);
        return this;
    }


    @Override
    public ConnectRequest fail(final FailCallback callback) {
        super.fail(callback);
        return this;
    }


    @Override
    public Request discall(DisConnectionCallback callback) {
        super.discall(callback);
        return this;
    }
}
