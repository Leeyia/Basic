package com.racofix.basic.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import com.racofix.basic.logger.LogUtil;

class BluetoothAdapter2Impl implements BluetoothAdapter2 {

    private final Context context;
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    public BluetoothAdapter2Impl(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
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

        if (!this.mBluetoothAdapter.isEnabled()) {
            this.mBluetoothAdapter.enable();
        }

        return this.mBluetoothAdapter;
    }

    @Override
    public boolean isEnable() {
        return this.getBluetoothAdapter().isEnabled();
    }

}
