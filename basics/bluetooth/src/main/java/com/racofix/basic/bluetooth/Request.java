package com.racofix.basic.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.ConditionVariable;
import android.support.annotation.NonNull;

public class Request {

    enum Type {
        CONNECT,
        DISCONNECT,
    }

    BluetoothKit bluetoothKit;

    final ConditionVariable syncLock;
    final Type type;

    SuccessCallback successCallback;
    FailCallback failCallback;

    Request(@NonNull final Type type) {
        this.type = type;
        this.syncLock = new ConditionVariable(true);
    }

    @NonNull
    static ConnectionRequest connect(@NonNull final String deviceMac) {
        return new ConnectionRequest(Type.CONNECT, deviceMac);
    }

    @NonNull
    static ConnectionRequest connect(@NonNull final BluetoothDevice device) {
        return new ConnectionRequest(Type.CONNECT, device);
    }

    @NonNull
    Request setManager(@NonNull final BluetoothKit bluetoothKit) {
        this.bluetoothKit = bluetoothKit;
        return this;
    }

    @NonNull
    public Request done(@NonNull final SuccessCallback callback) {
        this.successCallback = callback;
        return this;
    }

    @NonNull
    public Request fail(@NonNull final FailCallback callback) {
        this.failCallback = callback;
        return this;
    }

    public void execute() {
        this.bluetoothKit.execute(this);
    }
}
