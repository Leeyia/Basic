package com.racofix.develop.things;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.racofix.develop.bluetooth.BleDevice;
import com.racofix.develop.bluetooth.BleScanCallback;
import com.racofix.develop.bluetooth.BluetoothKit;
import com.racofix.develop.bluetooth.ScanConfig;

import java.util.List;

public class MainActivity extends Activity implements BleScanCallback, AdapterView.OnItemClickListener, View.OnClickListener {

    private BluetoothKit kit;
    private CommonAdapter<BleDevice> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.cancel_btn).setOnClickListener(this);
        ListView mBleListView = findViewById(R.id.ble_list_view);

        adapter = new CommonAdapter<BleDevice>(this, R.layout.item_ble) {
            @Override
            public void convert(ViewHolder helper, BleDevice item) {
                helper.setText(R.id.ble_name, item.getDevice().getName() + "             " + item.getRssi());
                helper.setText(R.id.ble_mac, item.getDevice().getAddress());
            }
        };
        mBleListView.setAdapter(adapter);
        mBleListView.setOnItemClickListener(this);

        ScanConfig config = new ScanConfig.Builder()
                .scanPeriodMills(1000)
                .scanBetweenMills(5000)
                .scanBLEFilters("Holy", "MI Band 2", "01:17:C5:98:4A:DE")
                .periodOpen(true)
                .removeDuplicate(false)
                .build();

        kit = BluetoothKit.getInstance(this);
        kit.setScanConfig(config);
        kit.setBleScanCallback(this);
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
        kit.stopLeScan();
    }

    private boolean checkSelfPermission2(String permission) {
//        if (checkSelfPermission2(Manifest.permission.ACCESS_COARSE_LOCATION) && checkSelfPermission2(Manifest.permission.ACCESS_FINE_LOCATION)) {
//            ActivityCompat
//                    .requestPermissions(null, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
//                    1);
//        }
        return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.e("text", "CAMERA permission has now been granted. Showing preview.");
                Toast.makeText(this, "权限已开", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("text", "CAMERA permission was NOT granted.");
                Toast.makeText(this, "权限已关", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BleDevice item = adapter.getItem(i);
        kit.connect(item.getDevice().getAddress());
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
