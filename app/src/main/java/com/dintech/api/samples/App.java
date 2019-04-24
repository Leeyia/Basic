package com.dintech.api.samples;

import android.app.Application;

import com.dintech.api.bleep.BluetoothKit;
import com.dintech.api.bleep.Configurations;
import com.dintech.api.bleep.operation.SystemOperation;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        VLog.initialize("com.dintech.getApiService.log")
//                .logLevel(LogLevel.FULL)
//                .logTool(new AndroidLogTool())
//                .methodCount(3)
//                .methodOffset(10);


        Configurations configures = Configurations.newBuilder()
                .serviceUuid(Configurations.UUID.SERVICE_UUID)
                .characterUuid(Configurations.UUID.CHARACTER_UUID)
                .operation(new SystemOperation())
                .setSplit(false)
                .build();
        BluetoothKit.init(this).configurations(configures);
    }
}
