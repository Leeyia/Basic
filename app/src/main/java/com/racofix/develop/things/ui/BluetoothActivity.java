package com.racofix.develop.things.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.racofix.develop.bluetooth.model.BleDevice;
import com.racofix.develop.bluetooth.BluetoothConfig;
import com.racofix.develop.bluetooth.BluetoothKit;
import com.racofix.develop.bluetooth.BluetoothKitImpl;
import com.racofix.develop.bluetooth.callback.BleConnectCallback;
import com.racofix.develop.bluetooth.callback.BleScanCallback;
import com.racofix.develop.things.R;
import com.racofix.develop.things.base.CommonAdapter;
import com.racofix.develop.things.base.ViewHolder;

import java.util.List;

public class BluetoothActivity extends Activity implements BleScanCallback, View.OnClickListener {

    private BluetoothKit kit;
    private CommonAdapter<BleDevice> adapter;
    private static final String TAG = BluetoothActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.cancel_btn).setOnClickListener(this);
        ListView mBleListView = findViewById(R.id.ble_list_view);
        String text = "8045002690020000000000000000000000000000000263C1100D00000000C8F0CEC2CAB3C6B7D3E3F6DF3635306763C1100D10001000C8F0CEC2CAB3C6B7D3E3F6DF3635306700";

        adapter = new CommonAdapter<BleDevice>(this, R.layout.item_ble) {
            @Override
            public void convert(ViewHolder helper, BleDevice item) {
                helper.setText(R.id.ble_name, item.getDevice().getName() + "             " + item.getRssi());
                helper.setText(R.id.ble_mac, item.getDevice().getAddress());
                helper.setText(R.id.ble_connect_state, item.connected ? "connect" : "disconnect");

                helper.getConvertView().setOnClickListener(view -> {

                    if (!item.connected) {

                        kit.getGattControll().connect(5000, item, new BleConnectCallback() {
                            @Override
                            public void onStart(boolean connectState, String info, BleDevice device) {

                            }

                            @Override
                            public void onConnect(BleDevice device) {
                                CharacteristicActy.startup(BluetoothActivity.this, device.getDevice().getAddress());
                            }

                            @Override
                            public void onTimeout(BleDevice device) {

                            }

                            @Override
                            public void onDisconnect(BleDevice device) {

                            }
                        });
                    } else {
                        CharacteristicActy.startup(BluetoothActivity.this, item.getDevice().getAddress());
                    }
                });
            }
        };
        mBleListView.setAdapter(adapter);

        BluetoothConfig config = new BluetoothConfig.Builder()
                .scanPeriodMills(5000)
                .scanBetweenMills(1000)
                .scanBLEFilters("mxdble-peripheral", "Holy")
                .periodOpen(true)
                .removeDuplicate(false)
                .build();

        kit = BluetoothKitImpl.getInstance(this);
        kit.setBluetoothConfig(config);
        kit.setBluetoothScanCallback(this);
        kit.startLeScan();
    }

    @Override
    public void onLeScan(BleDevice device) {
        Log.d("test", device.getDevice().getName() + " mac: " + device.getDevice().getAddress() + " rssi: " + device.getRssi());
    }

    @Override
    public void onScanPeriodFinish(List<BleDevice> devices) {
        runOnUiThread(() -> {
            adapter.refreshDatas(devices);
            Log.i("test", "onScanPeriodFinish = " + devices.size() + "个");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        kit.onDestory();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:

                break;

            case R.id.cancel_btn:
                kit.stopLeScan();
                break;
        }
    }
}
