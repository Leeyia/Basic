package com.racofix.basic.bluetooth.connection;

import android.bluetooth.BluetoothGatt;

/**
 * List of common GATT error codes.
 */
public enum GattError {
    
    CONNECTION_CONGESTED(BluetoothGatt.GATT_CONNECTION_CONGESTED),
    FAILURE(BluetoothGatt.GATT_FAILURE),
    INSUFFICIENT_AUTHENTICATION(BluetoothGatt.GATT_INSUFFICIENT_AUTHENTICATION),
    INSUFFICIENT_ENCRYPTION(BluetoothGatt.GATT_INSUFFICIENT_ENCRYPTION),
    INVALID_ATTRIBUTE_LENGTH(BluetoothGatt.GATT_INVALID_ATTRIBUTE_LENGTH),
    INVALID_OFFSET(BluetoothGatt.GATT_INVALID_OFFSET),
    READ_NOT_PERMITTED(BluetoothGatt.GATT_READ_NOT_PERMITTED),
    REQUEST_NOT_SUPPORTED(BluetoothGatt.GATT_REQUEST_NOT_SUPPORTED),
    SUCCESS(BluetoothGatt.GATT_SUCCESS),
    WRITE_NOT_PERMITTED(BluetoothGatt.GATT_WRITE_NOT_PERMITTED),
    GENERIC_ERROR(133),
    DISCONNECTED(-2),
    UNKNOWN(null);

    GattError(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public final Integer errorCode;

    public static GattError getGettError(int errorCode) {
        for (GattError error : values()) {
            if (error.errorCode != null && error.errorCode == errorCode) {
                return error;
            }
        }
        return UNKNOWN;
    }
}
