package com.racofix.basic.bluetooth;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.racofix.basic.bluetooth.conf.ContextWrf;
import com.racofix.basic.bluetooth.utils.Preconditions;

public class BluetoothKit {

    private static BluetoothKit bluetoothKit;

    private boolean mInitialConnection;
    private boolean mConnected;
    private long mConnectionTime;

    private BluetoothGatt mBluetoothGatt;
    private BluetoothDevice mBluetoothDevice;

    public static void initialize(Application application) {
        ContextWrf.initialize(application.getApplicationContext());
    }

    public static BluetoothKit getDefalut() {
        if (bluetoothKit == null) {
            synchronized (BluetoothKit.class) {
                if (bluetoothKit == null) bluetoothKit = new BluetoothKit();
            }
        }
        return bluetoothKit;
    }

    private BluetoothKit() {
        Preconditions.checkNotNull(ContextWrf.get(), "You need to initialize BluetoothKit first. " +
                "BluetoothKit.initialize(application)");
    }

    @NonNull
    public ConnectionRequest connection(@NonNull String deviceMac) {
        if (deviceMac == null) {
            throw new NullPointerException("Bluetooth device not specified");
        }
        return Request.connect(deviceMac)
                .setManager(this);
    }

    public void execute(Request request) {
        switch (request.type) {
            case CONNECT:
//                internalConnect();
                Log.d(getClass().getSimpleName(),"connection");
                break;

            case DISCONNECT:
//                internalConnect();
                Log.d(getClass().getSimpleName(),"disconnect");
                break;
        }
    }

    @MainThread
    private boolean internalConnect(@NonNull final String device,
                                    @Nullable final ConnectionRequest connectRequest) {
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(device);
        bluetoothDevice.connectGatt(ContextWrf.get(), true, new BluetoothGattCallback() {
        });
        return true;
    }
}
