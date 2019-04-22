package com.dintech.api.bleep.callback;

import android.bluetooth.BluetoothDevice;

public interface NotificationCallback {
    void onNotificationChanged(BluetoothDevice device, byte[] data);
}
