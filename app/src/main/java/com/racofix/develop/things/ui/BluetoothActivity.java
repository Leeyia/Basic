package com.racofix.develop.things.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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

        adapter = new CommonAdapter<BleDevice>(this, R.layout.item_ble) {
            @Override
            public void convert(ViewHolder helper, BleDevice item) {
                helper.setText(R.id.ble_name, item.getDevice().getName() + "             " + item.getRssi());
                helper.setText(R.id.ble_mac, item.getDevice().getAddress());
                helper.setText(R.id.ble_connect_state, item.connected ? "connect" : "disconnect");

                helper.getConvertView().setOnClickListener(view -> {
                    if (!item.connected) {

                        kit.connect(item, new BleConnectCallback() {
                            @Override
                            public void onStart(boolean newState, String info, BleDevice device) {
                                Log.e(TAG, "start connecting = " + newState + "     info: " + info);
                            }

                            @Override
                            public void onConnect(BleDevice device) {
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onTimeout(BleDevice device) {
                                Toast.makeText(BluetoothActivity.this, "connect timeout!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDisconnect(BleDevice device) {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        kit.disconnect(item);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        mBleListView.setAdapter(adapter);

        BluetoothConfig config = new BluetoothConfig.Builder()
                .scanPeriodMills(1000)
                .scanBetweenMills(5000)
                .scanBLEFilters("Holy", "MI Band 2")
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
            case R.id.cancel_btn:
                kit.stopLeScan();
                break;
        }
    }
}
