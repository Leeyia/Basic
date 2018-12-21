package com.racofix.basic.things.mvp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.racofix.basic.bluetooth.BlueRock;
import com.racofix.basic.bluetooth.BluetoothKit;
import com.racofix.basic.bluetooth.conf.ScanConfigure;
import com.racofix.basic.bluetooth.scanning.ScanBLERecord;
import com.racofix.basic.bluetooth.scanning.ScanOperation;
import com.racofix.basic.image.GlideImageEngine;
import com.racofix.basic.image.ImageConfigure;
import com.racofix.basic.image.ImageLoader;
import com.racofix.basic.image.PicassoImageEngine;
import com.racofix.basic.mvp.BaseActivity;
import com.racofix.basic.mvp.annotation.Implement;
import com.racofix.basic.things.R;

@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo, BlueRock.OnScanCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(view -> getLogicImpl().login("admin", "admin"));

        ImageView target = findViewById(R.id.iv_avatar);

        ImageLoader
                .getDefalut()
                .engine(new GlideImageEngine())
                .display(new ImageConfigure.Configure()
                        .imageUrl("https://avatars2.githubusercontent.com/u/16660064?s=460&v=4")
                        .into(target)
                        .build());


        ScanConfigure configure = new ScanConfigure.Configure()
                .periodMills(1000)
                .waitMills(5000)
                .filters("BA:S7:D5:U6")
                .conf();

        ScanOperation operation = BluetoothKit.getDefalut().getScanProxy();
        operation.setScanConfig(configure);
        operation.addOnScanCallback(this);
        operation.start();
    }

    @Override
    public void successful(String message) {
    }

    @Override
    public void failed(String failMsg) {
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, ScanBLERecord scanRecord, long timestamp) {

    }

    @Override
    public void onScanCycleCompleted() {

    }

    @Override
    public void onError(int errorId) {

    }
}
