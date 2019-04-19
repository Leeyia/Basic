package com.dintech.api.bluetooth.callback;

import android.bluetooth.BluetoothDevice;

public interface NotificationCallback {
    void onNotificationChanged(BluetoothDevice device, byte[] data);
}
