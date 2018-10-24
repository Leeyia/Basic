package com.racofix.develop.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import com.racofix.develop.logger.LogUtil;

class BluetoothLogicImpl implements BluetoothLogic {

    private final Context context;
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    public BluetoothLogicImpl(Context context) {
        this.context = context;
    }

    @Override
    public BluetoothAdapter getBluetoothAdapter() {

        if (this.context == null) {
            LogUtil.e("", "Unable to obtain a BluetoothAdapter because context null");
            return null;
        }

        if (this.mBluetoothManager == null) {
            this.mBluetoothManager = (BluetoothManager) this.context.getSystemService(Context.BLUETOOTH_SERVICE);
            if (this.mBluetoothManager == null) {
                LogUtil.e("", "Unable to initialize BluetoothManager.");
                return null;
            }
        }
        this.mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (this.mBluetoothAdapter == null) {
            LogUtil.e("", "Unable to obtain a BluetoothAdapter.");
            return null;
        }
        return this.mBluetoothAdapter;
    }
}
