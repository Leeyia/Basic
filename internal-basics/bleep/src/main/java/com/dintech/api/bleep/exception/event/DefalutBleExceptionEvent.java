package com.dintech.api.bleep.exception.event;

import android.util.Log;

import com.dintech.api.bleep.exception.ConnectedException;

public class DefalutBleExceptionEvent extends BleExceptionEvent {

    @Override
    void onEventConnected(ConnectedException e) {
        Log.e("", e.getDescription());
    }
}
