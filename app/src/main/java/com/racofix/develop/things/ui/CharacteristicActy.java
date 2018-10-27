package com.racofix.develop.things.ui;

import android.app.Activity;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.racofix.develop.bluetooth.BluetoothGattControll;
import com.racofix.develop.bluetooth.BluetoothKit;
import com.racofix.develop.bluetooth.BluetoothKitImpl;
import com.racofix.develop.bluetooth.callback.OnWriteCallback;
import com.racofix.develop.bluetooth.model.BleDevice;
import com.racofix.develop.things.GbkCode;

import java.util.List;

public class CharacteristicActy extends Activity {

    public static void startup(Context context, String bluetoothAddress) {
        Intent intent = new Intent(context, CharacteristicActy.class);
        intent.putExtra("bluetoothAddress", bluetoothAddress);
        context.startActivity(intent);
    }

    private String address;
    private final String TAG = "CharacteristicActy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        address = getIntent().getStringExtra("bluetoothAddress");
        String message = "8045002690020000000000000000000000000000000263C1100D00000000C8F0CEC2CAB3C6B7D3E3F6DF3635306763C1100D10001000C8F0CEC2CAB3C6B7D3E3F6DF3635306700";
        byte[] values = GbkCode.hexStringToBytes(message);
        int length = values.length;
        BluetoothKit bluetoothKit = BluetoothKitImpl.getInstance(this);
        BluetoothGattControll connect = bluetoothKit.getGattControll();

        List<BleDevice> connectedDevices = connect.getConnectedDevices();

        BluetoothGatt bluetoothGatt = connect.getBluetoothGatt(address);

        BleDevice bleDevice = connectedDevices.get(0);

        connect.write(bleDevice, "0000fff0-0000-1000-8000-00805f9b34fb", "0000fff3-0000-1000-8000-00805f9b34fb", values, new OnWriteCallback() {
            @Override
            public void failure(int failCode, String info, BleDevice device) {
                BleDevice device1 = device;
                String info1 = info;
            }

            @Override
            public void writed(byte[] bytes, BleDevice device) {
                byte[] data1 = bytes;
            }
        });

        // mBluetoothGatt = device.connectGatt(this, false, mGattCallback);
        //设备属性
//        BluetoothDevice remoteDevice = bluetoothKit.getBluetoothAdapter().getRemoteDevice(address);
//        List<BluetoothGattService> services = bluetoothGatt.getServices();
//
//        BluetoothGattService bluetoothGattService = bluetoothGatt.getService(UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb"));
//        List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
//
//        //获取writeCharacteristic -
//        BluetoothGattCharacteristic writeCharacteristic = bluetoothGattService.getCharacteristic(UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb"));
//        writeCharacteristic.getService().getUuid();
//
//        writeCharacteristic.setValue(values);
//
//        //写入 -
//        bluetoothGatt.writeCharacteristic(writeCharacteristic);
    }
}
