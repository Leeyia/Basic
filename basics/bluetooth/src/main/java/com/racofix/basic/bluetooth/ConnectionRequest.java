package com.racofix.basic.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

public class ConnectionRequest extends Request {

    ConnectionRequest(@NonNull Type type, @NonNull final String device) {
        super(type);

    }

    ConnectionRequest(@NonNull Type type, @NonNull final BluetoothDevice device) {
        super(type);
    }

    @NonNull
    @Override
    ConnectionRequest setManager(@NonNull BluetoothKit bluetoothKit) {
        super.setManager(bluetoothKit);
        return this;
    }
}
