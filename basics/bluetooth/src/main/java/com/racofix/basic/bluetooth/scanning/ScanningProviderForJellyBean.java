package com.racofix.basic.bluetooth.scanning;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;

import com.racofix.basic.bluetooth.utils.L;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class ScanningProviderForJellyBean extends ScanningProviderFactory {

    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (getScanCallback() != null) {
                ScanBLERecord scanBLERecord = ScanBLERecord.parseFromBytes(scanRecord);
                getScanCallback().onLeScan(device, rssi, scanBLERecord, System.currentTimeMillis());
            }
        }
    };

    @SuppressLint("MissingPermission")
    protected void startScan() {
        L.d("start scan in Jelly Bean");
        this.getBluetoothAdapter().startLeScan(this.leScanCallback);
    }

    @SuppressLint("MissingPermission")
    protected void stopScan() {
        L.d("stop scan in Jelly Bean");
        this.getBluetoothAdapter().stopLeScan(this.leScanCallback);
    }
}
