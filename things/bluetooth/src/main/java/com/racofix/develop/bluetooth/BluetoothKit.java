package com.racofix.develop.bluetooth;

import android.bluetooth.BluetoothAdapter;

import com.racofix.develop.bluetooth.callback.BleScanCallback;
import com.racofix.develop.bluetooth.model.BleDevice;

public interface BluetoothKit {

    void startLeScan();

    void stopLeScan();

    BluetoothGattControll getGattControll();

    void checkBleGattInterceptor();

    boolean isScanning();

    boolean isConnected(BleDevice device);

    boolean isOpenFiltered();

    void setBluetoothConfig(BluetoothConfig config);

    void setBluetoothScanCallback(BleScanCallback scanCallback);

    void onDestory();

    BluetoothAdapter getBluetoothAdapter();
}
