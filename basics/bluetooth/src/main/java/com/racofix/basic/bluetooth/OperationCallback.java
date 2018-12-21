package com.racofix.basic.bluetooth;

import com.racofix.basic.bluetooth.connection.GattError;

public interface OperationCallback<T> {
    void onSuccess(T characteristic);

    void onFailure(GattError gattError, int errorCode);
}
