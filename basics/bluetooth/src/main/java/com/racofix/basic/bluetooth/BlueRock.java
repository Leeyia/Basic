package com.racofix.basic.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;

import com.racofix.basic.bluetooth.connection.GattError;
import com.racofix.basic.bluetooth.scanning.ScanBLERecord;

import java.util.List;

public interface BlueRock {

    interface ConnectionCallback {
        /* Connected to the device. Returns list of discovered services. */
        void onConnected(List<BluetoothGattService> services);

        /* Connecting to device failed. */
        void onConnectionError(GattError gattError, String errorMsg);

        /* Device disconnected. Underlying connection was closed and released. */
        void onDisconnected();
    }

    interface OnScanCallback {
        void onLeScan(BluetoothDevice device, int rssi, ScanBLERecord scanRecord, long timestamp);

        void onScanCycleCompleted();

        void onError(int errorId);
    }
}
