package com.racofix.basic.things.application;

import com.racofix.basic.bluetooth.BluetoothKit;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BluetoothKit.initialize(this);
    }
}
