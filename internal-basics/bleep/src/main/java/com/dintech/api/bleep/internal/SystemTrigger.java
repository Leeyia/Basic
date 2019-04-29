package com.dintech.api.bleep.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.dintech.api.bleep.Request;
import com.dintech.api.bleep.Trigger;
import com.dintech.api.bleep.exception.ConnectedException;

import java.util.ArrayList;
import java.util.List;

final class SystemTrigger implements Trigger {

    private Context mContext;
    private boolean mConnectExecuted;
    private List<String> mConnectedDevices;
    private Object mLock = new Object();

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
        }
    };

    public SystemTrigger(Context context) {
        this.mContext = context;
        this.mConnectedDevices = new ArrayList<>();
    }

    @Override
    @MainThread
    public void connect(@NonNull BluetoothDevice device, Request mConnectRequest) {
        final boolean bluetoothEnabled = BluetoothAdapter.getDefaultAdapter().isEnabled();
        //蓝牙为开启或正在连接 直接更新状态 -> 下一个请求
        if (mConnectedDevices.contains(device.getAddress()) || !bluetoothEnabled) {
            if (bluetoothEnabled) {
                mConnectRequest.notifySuccess(device);
            } else {
                // We can't return false here, as the request would be notified with
                // mBluetoothDevice instance instead, and that may be null or a wrong device.
                if (mConnectRequest != null) {
                    mConnectRequest.notifyFail(device,
                            new ConnectedException(1000, "Connect Exception Occurred Of :" + "Bluetooth Close"));
                } // else, the request was already failed by the Bluetooth state receiver
            }

            int mConnectionState = BluetoothGatt.STATE_CONNECTING;
            synchronized (mLock) {
                //fix 重连
            }

            long mConnectionTime = SystemClock.elapsedRealtime();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // connectRequest will never be null here.

                // A variant of connectGatt with Handled can't be used here.
                // Check https://github.com/NordicSemiconductor/Android-BLE-Library/issues/54
                BluetoothGatt mBluetoothGatt = device.connectGatt(mContext, false, mGattCallback,
                        BluetoothDevice.TRANSPORT_LE/*,, preferredPhy/*, mHandler*/);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                BluetoothGatt mBluetoothGatt = device.connectGatt(mContext, false, mGattCallback,
                        BluetoothDevice.TRANSPORT_LE);
            } else {
                BluetoothGatt mBluetoothGatt = device.connectGatt(mContext, false, mGattCallback);
            }
        }
    }

    @Override
    public void notification(BluetoothDevice device, Request request) {
    }

    @Override
    public void write(BluetoothDevice device, byte[] bytes, Request request) {
    }

    @Override
    public void disconnect(BluetoothDevice device, Request request) {
    }
}
