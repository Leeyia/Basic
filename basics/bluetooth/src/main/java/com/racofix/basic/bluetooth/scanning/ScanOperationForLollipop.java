package com.racofix.basic.bluetooth.scanning;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Build;

import com.racofix.basic.bluetooth.utils.L;

import java.util.Iterator;
import java.util.List;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ScanOperationForLollipop extends BaseScanOperation {

    private long datatime = 0L;
    private BluetoothLeScanner bluetoothLeScanner;

    private ScanCallback leScanCallback = new ScanCallback() {
        public void onScanResult(int callbackType, ScanResult scanResult) {
            L.d("onScanResult");
            if (datatime == 0L) {
                datatime = System.currentTimeMillis();
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long intervalTimeMillis = currentTimeMillis - datatime;
                ScanOperationForLollipop.this.datatime = currentTimeMillis;
                L.d("the time it took for the last reception:=====>" + intervalTimeMillis + "ms");
                L.d("device.address:=====>" + scanResult.getDevice().getAddress());
            }
            if (getScanCallback() != null) {
                ScanRecord scanRecord = scanResult.getScanRecord();
                ScanBLERecord scanBLERecord = new ScanBLERecord(scanRecord.getServiceUuids(), scanRecord.getManufacturerSpecificData(), scanRecord.getServiceData(), scanRecord.getAdvertiseFlags(), scanRecord.getTxPowerLevel(), scanRecord.getDeviceName(), scanRecord.getBytes());
                getScanCallback().onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanBLERecord, datatime);
            }
        }

        public void onBatchScanResults(List<ScanResult> results) {
            Iterator var2 = results.iterator();
            while (var2.hasNext()) {
                ScanResult scanResult = (ScanResult) var2.next();

                if (getScanCallback() != null) {
                    ScanRecord scanRecord = scanResult.getScanRecord();
                    ScanBLERecord scanBLERecord = new ScanBLERecord(scanRecord.getServiceUuids(), scanRecord.getManufacturerSpecificData(), scanRecord.getServiceData(), scanRecord.getAdvertiseFlags(), scanRecord.getTxPowerLevel(), scanRecord.getDeviceName(), scanRecord.getBytes());
                    getScanCallback().onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanBLERecord, scanResult.getTimestampNanos());
                }

            }
        }

        public void onScanFailed(int errorCode) {
            L.d("onScanFailed errorCode: " + errorCode);
            if (getScanCallback() != null) getScanCallback().onError(errorCode);
        }
    };

    @Override
    @SuppressLint("MissingPermission")
    protected void startScan() {
        L.d("start scan in Lollipop");
        try {
            if (this.getScanner() != null) {
                this.getScanner().startScan(leScanCallback);
            }
        } catch (IllegalStateException var2) {
            L.d("Cannot start scan.  Bluetooth may be turned off.");
        }
    }

    @Override
    @SuppressLint("MissingPermission")
    protected void stopScan() {
        L.d("stop scan in Lollipop");
        if (getBluetoothAdapter() != null
                && getBluetoothAdapter().getState() == BluetoothAdapter.STATE_ON) {
            this.getScanner().stopScan(this.leScanCallback);
        } else {
            L.d("BT Adapter is not turned ON");
        }
    }

    private BluetoothLeScanner getScanner() {
        if (this.bluetoothLeScanner == null) {
            L.d("Making new Android L scanner");
            this.bluetoothLeScanner = this.getBluetoothAdapter().getBluetoothLeScanner();
            if (this.bluetoothLeScanner == null) {
                L.d("Failed to make new Android L scanner");
            }
        }
        return this.bluetoothLeScanner;
    }
}
