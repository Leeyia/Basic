package com.dintech.api.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

import com.dintech.api.bluetooth.callback.DisConnectionCallback;
import com.dintech.api.bluetooth.callback.FailCallback;
import com.dintech.api.bluetooth.callback.SuccessCallback;

public class ConnectRequest extends Request {

    ConnectRequest(@NonNull final Type type, @NonNull final BluetoothDevice device) {
        super(type, device);
    }

    @NonNull
    @Override
    ConnectRequest setBluetoothKit(@NonNull final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @NonNull
    @Override
    public ConnectRequest done(@NonNull final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @NonNull
    @Override
    public ConnectRequest fail(@NonNull final FailCallback callback) {
        super.fail(callback);
        return this;
    }

    @NonNull
    @Override
    public Request discall(@NonNull DisConnectionCallback callback) {
        super.discall(callback);
        return this;
    }
}
