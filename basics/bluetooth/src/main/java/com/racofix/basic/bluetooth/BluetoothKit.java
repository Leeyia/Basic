package com.racofix.basic.bluetooth;

import android.bluetooth.BluetoothAdapter;

import com.racofix.basic.bluetooth.callback.BleScanCallback;
import com.racofix.basic.bluetooth.model.BleDevice;

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
