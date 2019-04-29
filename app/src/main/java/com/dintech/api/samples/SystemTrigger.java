package com.dintech.api.samples;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;

import com.dintech.api.bleep.Blueteeth;
import com.dintech.api.bleep.ConnectedRequest;
import com.dintech.api.bleep.Request;
import com.dintech.api.bleep.exception.BleException;
import com.dintech.api.bleep.exception.ConnectedException;
import com.dintech.api.bleep.Trigger;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.Code;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;

import java.util.UUID;

public class SystemTrigger implements Trigger {
    private final BluetoothClient mClient;

    public SystemTrigger(Context context) {
        this.mClient = new BluetoothClient(context);
    }

    @Override
    public void connect(BluetoothDevice device, Request request) {
        ConnectedRequest cr = (ConnectedRequest) request;
        BleConnectOptions options = new BleConnectOptions.Builder().setConnectTimeout(10000).setConnectRetry(cr.getRetries()).setConnectTimeout(cr.getTimeout()).build();
        this.mClient.connect(device.getAddress(), options, (code, data) -> {
            if (code == Code.REQUEST_SUCCESS) {
                Log.d("ConnectEvent", "Connect Succ: " + device.getAddress());
                request.notifySuccess(device);
            } else {
                request.notifyFail(device, new ConnectedException(code, Code.toString(code)));
            }
        });
    }

    @Override
    public void disconnect(BluetoothDevice device, Request request) {
        mClient.disconnect(device.getAddress());
    }

    @Override
    public void notification(BluetoothDevice device, Request request) {
        UUID serviceUuid = UUID.fromString(Blueteeth.getConfigurations().getServiceUuid());
        UUID characterUuid = UUID.fromString(Blueteeth.getConfigurations().getCharacterUuid());

        mClient.notify(device.getAddress(), serviceUuid, characterUuid, new BleNotifyResponse() {
            @Override
            public void onNotify(UUID service, UUID character, byte[] data) {
                request.notifyNotification(device, data);
            }

            @Override
            public void onResponse(int code) {
                if (code == Code.REQUEST_SUCCESS) {
                    request.notifySuccess(device);
                } else {
                    request.notifyFail(device, new BleException(code, Code.toString(code)));
                }
            }
        });
    }

    @Override
    public void write(BluetoothDevice device, byte[] bytes, Request request) {
        UUID serviceUuid = UUID.fromString(Blueteeth.getConfigurations().getServiceUuid());
        UUID characterUuid = UUID.fromString(Blueteeth.getConfigurations().getCharacterUuid());

        mClient.write(device.getAddress(), serviceUuid, characterUuid, bytes, code -> {
            if (code == Code.REQUEST_SUCCESS) {
                request.notifySuccess(device);
            } else {
                request.notifyFail(device, new BleException(code, Code.toString(code)));
            }
        });
    }
}
