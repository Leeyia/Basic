package com.dintech.api.bleep.operation;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

import com.dintech.api.bleep.BluetoothKit;
import com.dintech.api.bleep.Request;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SystemOperation implements Operation {

    private final Context context;
    private Map<BluetoothDevice, BluetoothGatt> mDeviceGattHashMap = new ConcurrentHashMap<>();

    public SystemOperation() {
        this.context = BluetoothKit.get()
                .getSettings()
                .getApplication()
                .getApplicationContext();
    }

    @Override
    public void connect(BluetoothDevice device, Request request) {
        device.connectGatt(context, false, new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                switch (newState) {
                    case BluetoothProfile.STATE_CONNECTED:
                        if (null != gatt) {
                            mDeviceGattHashMap.put(device, gatt);
                            request.notifySuccess(device);
                        } else throw new NullPointerException("bluetoothGatt is null");
                        break;
                    case BluetoothProfile.STATE_DISCONNECTED:
                        if (null != gatt) {
                            mDeviceGattHashMap.remove(gatt);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void disconnect(BluetoothDevice device, Request request) {
        BluetoothGatt gatt = mDeviceGattHashMap.get(device);
        if (gatt != null) {
            gatt.disconnect();
            request.notifySuccess(device);
        }
    }

    @Override
    public void notification(BluetoothDevice device, String serviceUuid, String characterUuid, Request request) {

    }

    @Override
    public void write(BluetoothDevice device, String serviceUuid, String characterUuid, byte[] data, boolean split, Request request) {
        BluetoothGatt gatt = mDeviceGattHashMap.get(device);
        BluetoothGattService service = gatt.getService(UUID.fromString(serviceUuid));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(characterUuid));
        characteristic.setValue(data);
        //设置回复形式
        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
        //开始写数据
        boolean successed = gatt.writeCharacteristic(characteristic);
        if (successed) request.notifySuccess(device);
    }
}
