package com.racofix.basic.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

public interface SuccessCallback {

    /**
     * A callback invoked when the request completed successfully.
     *
     * @param device the target device.
     */
    void onRequestCompleted(@NonNull final BluetoothDevice device);
}
