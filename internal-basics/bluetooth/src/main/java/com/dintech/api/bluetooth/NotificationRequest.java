package com.dintech.api.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

import com.dintech.api.bluetooth.callback.FailCallback;
import com.dintech.api.bluetooth.callback.NotificationCallback;
import com.dintech.api.bluetooth.callback.SuccessCallback;

public class NotificationRequest extends Request {

    NotificationRequest(@NonNull final Type type, @NonNull final BluetoothDevice device) {
        super(type, device);
    }

    @NonNull
    @Override
    NotificationRequest setBluetoothKit(@NonNull final BluetoothKit manager) {
        super.setBluetoothKit(manager);
        return this;
    }

    @NonNull
    @Override
    public NotificationRequest done(@NonNull final SuccessCallback callback) {
        super.done(callback);
        return this;
    }

    @NonNull
    @Override
    public NotificationRequest fail(@NonNull final FailCallback callback) {
        super.fail(callback);
        return this;
    }

    @NonNull
    @Override
    public Request notify(@NonNull NotificationCallback callback) {
        super.notify(callback);
        return this;
    }
}
