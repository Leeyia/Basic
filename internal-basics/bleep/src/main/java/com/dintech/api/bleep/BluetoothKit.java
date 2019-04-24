package com.dintech.api.bleep;

import android.app.Application;
import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.FailCallback;

public class BluetoothKit implements FailCallback {

    private RequestQueue mRequestQueue = new RequestQueue();
    private static Settings settings;
    private static BluetoothKit bluetoothKit;

    public static BluetoothKit getInstance() {
        if (bluetoothKit == null) synchronized (BluetoothKit.class) {
            if (bluetoothKit == null) bluetoothKit = new BluetoothKit();
        }
        return bluetoothKit;
    }

    public static Settings init(Application application) {
        if (settings == null) settings = new Settings();
        settings.application(application);
        return settings;
    }

    private BluetoothKit() {
        Preconditions.checkNotNull(settings, "BluetoothKit NOT INIT");
    }

    public final ConnectRequest connect(final BluetoothDevice device) {
        return Request.connect(device).setBluetoothKit(this);
    }

    public final NotificationRequest notification(final BluetoothDevice device) {
        return Request.notify(device).setBluetoothKit(this);
    }

    public final WriteRequest write(final BluetoothDevice device, byte[] data) {
        return Request.write(device, data).setBluetoothKit(this);
    }

    public final ConnectRequest disconnect(final BluetoothDevice device) {
        return Request.disconnet(device).setBluetoothKit(this);
    }

    public Settings getSettings() {
        return Preconditions.checkNotNull(settings);
    }

    public Configurations getConfigurations() {
        return Preconditions.checkNotNull(settings.getConfiguration());
    }

    public void onDestory() {
        this.mRequestQueue.clearQueue();
    }

    final void enqueue(final Request request) {
        this.mRequestQueue.add(request);
        UiThread.getInstance().runOnUiThread(this::nextRequest);
    }

    @Override
    public void onRequestFailed(BluetoothDevice device, String message) {
        UiThread.getInstance().runOnUiThread(this::nextRequest);
    }

    private void nextRequest() {
        Request request = null;
        // If Request set is present, try taking next request from it
        if (mRequestQueue.hasMore()) {
            request = mRequestQueue.getNext();
        }
        if (request == null) return;

        switch (request.getType()) {
            case CONNECT:
                ConnectRequest cr = (ConnectRequest) request;
                getConfigurations().getOperation().connect(cr.getDevice(), cr);
                break;

            case NOTIFICATION:
                NotificationRequest nr = (NotificationRequest) request;
                getConfigurations().getOperation().notification(nr.getDevice(), getConfigurations().getServiceUuid(), getConfigurations().getCharacteristicUuid(), nr);
                break;

            case WRITE:
                WriteRequest wr = (WriteRequest) request;
                getConfigurations().getOperation().write(wr.getDevice(), getConfigurations().getServiceUuid(), getConfigurations().getCharacteristicUuid(), wr.getBytes(), getConfigurations().isSplit(), wr);
                break;

            case DISCONNECT:
                ConnectRequest dr = (ConnectRequest) request;
                settings.getConfiguration().getOperation().disconnect(dr.getDevice(), dr);
                break;
            default:
                break;
        }
    }
}