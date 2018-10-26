package com.racofix.develop.bluetooth;


import android.bluetooth.BluetoothGatt;

import com.racofix.develop.bluetooth.callback.BleConnectCallback;
import com.racofix.develop.bluetooth.callback.BleMtuCallback;
import com.racofix.develop.bluetooth.callback.BleNotifyCallback;
import com.racofix.develop.bluetooth.callback.BleReadCallback;
import com.racofix.develop.bluetooth.callback.BleRssiCallback;
import com.racofix.develop.bluetooth.callback.BleWriteByBatchCallback;
import com.racofix.develop.bluetooth.callback.BleWriteCallback;
import com.racofix.develop.bluetooth.model.BleDevice;
import com.racofix.develop.bluetooth.model.CharacteristicEntity;
import com.racofix.develop.bluetooth.model.ServiceEntity;

import java.util.List;
import java.util.Map;

public interface GattConnect {
    void connect(int connectTimeout, BleDevice device, BleConnectCallback callback);

    void disconnect(String address);

    void disconnectAll();

    void notify(BleDevice device, String serviceUuid, String notifyUuid, BleNotifyCallback callback);

    void cancelNotify(BleDevice device, String serviceUuid, String characteristicUuid);

    void read(BleDevice device, String serviceUuid, String readUuid, BleReadCallback callback);

    void write(BleDevice device, String serviceUuid, String writeUuid, byte[] data, BleWriteCallback callback);

    void writeByBatch(BleDevice device, String serviceUuid, String writeUuid, byte[] data, int lengthPerPackage, BleWriteByBatchCallback callback);

    void readRssi(BleDevice device, BleRssiCallback callback);

    void setMtu(BleDevice device, int mtu, BleMtuCallback callback);

    List<BleDevice> getConnectedDevices();

    Map<ServiceEntity, List<CharacteristicEntity>> getDeviceServices(BleDevice device);

    BluetoothGatt getBluetoothGatt(String address);

    void onDestory();
}
