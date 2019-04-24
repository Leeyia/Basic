package com.dintech.api.bleep.exception;

import android.bluetooth.BluetoothGatt;

import com.dintech.api.bleep.R;
import com.dintech.api.bleep.utils.ResourceUtil;

public class ConnectedException extends BleException {

    private BluetoothGatt gatt;
    private int newState;

    public ConnectedException(BluetoothGatt gatt, int newState) {
        super(1011, ResourceUtil.getString(R.string.exception_connected));
        this.gatt = gatt;
        this.newState = newState;
    }

    public BluetoothGatt getGatt() {
        return gatt;
    }

    public void setGatt(BluetoothGatt gatt) {
        this.gatt = gatt;
    }

    public int getNewState() {
        return newState;
    }

    public void setNewState(int newState) {
        this.newState = newState;
    }

    @Override
    public String toString() {
        return "ConnectedException{" +
                "gatt=" + gatt +
                ", newState=" + newState +
                '}';
    }
}
