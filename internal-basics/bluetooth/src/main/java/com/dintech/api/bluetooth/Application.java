package com.dintech.api.bluetooth;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configurations configuration = new Configurations.Builder().build();
        BluetoothKit
                .init(this)
                .configurations(configuration);
    }
}
