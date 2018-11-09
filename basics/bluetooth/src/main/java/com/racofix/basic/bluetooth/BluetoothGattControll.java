package com.racofix.basic.bluetooth;


import android.bluetooth.BluetoothGatt;

import com.racofix.basic.bluetooth.callback.BleConnectCallback;
import com.racofix.basic.bluetooth.callback.BleMtuCallback;
import com.racofix.basic.bluetooth.callback.BleNotifyCallback;
import com.racofix.basic.bluetooth.callback.BleReadCallback;
import com.racofix.basic.bluetooth.callback.BleRssiCallback;
import com.racofix.basic.bluetooth.callback.BleWriteByBatchCallback;
import com.racofix.basic.bluetooth.callback.OnWriteCallback;
import com.racofix.basic.bluetooth.model.BleDevice;
import com.racofix.basic.bluetooth.model.CharacteristicEntity;
import com.racofix.basic.bluetooth.model.ServiceEntity;

import java.util.List;
import java.util.Map;

public interface BluetoothGattControll {
    void connect(int connectTimeout, BleDevice device, BleConnectCallback callback);

    void disconnect(String address);

    void disconnectAll();

    void notify(BleDevice device, String serviceUuid, String notifyUuid, BleNotifyCallback callback);

    void cancelNotify(BleDevice device, String serviceUuid, String characteristicUuid);

    void read(BleDevice device, String serviceUuid, String readUuid, BleReadCallback callback);

    void write(BleDevice device, String serviceUuid, String writeUuid, byte[] data, OnWriteCallback callback);

    void writeByBatch(BleDevice device, String serviceUuid, String writeUuid, byte[] data, int lengthPerPackage, BleWriteByBatchCallback callback);

    void readRssi(BleDevice device, BleRssiCallback callback);

    void setMtu(BleDevice device, int mtu, BleMtuCallback callback);

    List<BleDevice> getConnectedDevices();

    Map<ServiceEntity, List<CharacteristicEntity>> getDeviceServices(BleDevice device);

    BluetoothGatt getBluetoothGatt(String address);

    void onDestory();
}
