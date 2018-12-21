package com.racofix.basic.bluetooth;

import android.bluetooth.BluetoothDevice;

import com.racofix.basic.bluetooth.scanning.ScanBLERecord;

public interface BlueRock {


    interface OnScanCallback {
        void onLeScan(BluetoothDevice device, int rssi, ScanBLERecord scanRecord, long timestamp);

        void onScanCycleCompleted();

        void onError(int errorId);
    }
}
