package com.racofix.basic.bluetooth.connection;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Build;

import com.racofix.basic.bluetooth.OperationCallback;

/**
 * Single write characteristic operation to Bluetooth device.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class GattCharacteristicWriteOperation implements GattOperation {

    private final BluetoothGattCharacteristic characteristic;
    private final OperationCallback callback;
    private final byte[] value;
    private final boolean noResponse;

    public GattCharacteristicWriteOperation(BluetoothGattCharacteristic characteristic, OperationCallback callback, boolean noResponse, byte[] value) {
        this.characteristic = characteristic;
        this.callback = callback;
        this.noResponse = noResponse;
        this.value = value;
    }

    @Override public void execute(BluetoothGatt gatt) {
        characteristic.setValue(value);
        characteristic.setWriteType(noResponse ? BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE : BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
        gatt.writeCharacteristic(characteristic);
    }

    public void onWrite(BluetoothGattCharacteristic characteristic, int status) {
        if (status == BluetoothGatt.GATT_SUCCESS) {
            callback.onSuccess(characteristic);
        } else {
            callback.onFailure(GattError.getGettError(status), status);
        }
    }
}
