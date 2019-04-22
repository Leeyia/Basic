package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

public class WriteRequest extends Request {

    private byte[] bytes;

    WriteRequest(final Type type, final BluetoothDevice device, byte[] bytes) {
        super(type, device);
        this.bytes = bytes;
    }

    @Override
    WriteRequest setBluetoothKit(final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }


    @Override
    public WriteRequest done(final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @Override
    public WriteRequest fail(final FailCallback callback) {
        super.fail(callback);
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
