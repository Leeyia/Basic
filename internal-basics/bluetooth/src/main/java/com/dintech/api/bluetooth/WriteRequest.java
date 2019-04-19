package com.dintech.api.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

import com.dintech.api.bluetooth.callback.FailCallback;
import com.dintech.api.bluetooth.callback.SuccessCallback;

public class WriteRequest extends Request {

    private byte[] data;

    WriteRequest(@NonNull final Type type, @NonNull final BluetoothDevice device, @NonNull byte[] data) {
        super(type, device);
        this.data = data;
    }

    @NonNull
    @Override
    WriteRequest setBluetoothKit(@NonNull final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @NonNull
    @Override
    public WriteRequest done(@NonNull final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @NonNull
    @Override
    public WriteRequest fail(@NonNull final FailCallback callback) {
        super.fail(callback);
        return this;
    }

    public byte[] getData() {
        return data;
    }
}
