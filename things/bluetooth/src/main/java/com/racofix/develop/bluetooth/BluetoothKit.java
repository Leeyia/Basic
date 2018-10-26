package com.racofix.develop.bluetooth;

import com.racofix.develop.bluetooth.callback.BleConnectCallback;
import com.racofix.develop.bluetooth.callback.BleScanCallback;
import com.racofix.develop.bluetooth.model.BleDevice;

public interface BluetoothKit {

    void startLeScan();

    void stopLeScan();

    void connect(String address, BleConnectCallback connectCallback);

    void connect(BleDevice device, BleConnectCallback connectCallback);

    void disconnect(String address);

    void disconnect(BleDevice device);

    void disconnectAll();

    void checkBleGattInterceptor();

    boolean isScanning();

    boolean isConnected(BleDevice device);

    boolean isOpenFiltered();

    boolean isConnectCallbackNotNull() throws NullPointerException;

    void setBluetoothConfig(BluetoothConfig config);

    void setBluetoothScanCallback(BleScanCallback scanCallback);

    void setBluetoothConnectCallback(BleConnectCallback connectCallback);

    void onDestory();
}
