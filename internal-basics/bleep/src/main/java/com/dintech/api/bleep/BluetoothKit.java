package com.dintech.api.bleep;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

public class BluetoothKit {

    private RequestQueue requestQueue;
    private static BluetoothKit bluetoothKit;
    private static Configurations.Builder newBuilder;

    private BluetoothKit() {
        if (newBuilder == null) throw new RuntimeException("BluetoothKit application not init");
        this.requestQueue = new RequestQueue();
    }

    public static BluetoothKit getInstance() {
        if (bluetoothKit == null) synchronized (BluetoothKit.class) {
            if (bluetoothKit == null) bluetoothKit = new BluetoothKit();
        }
        return bluetoothKit;
    }

    /*init configurations*/
    public static Configurations.Builder init() {
        if (newBuilder == null) synchronized (Configurations.class) {
            if (newBuilder == null) newBuilder = Configurations.newBuilder();
        }
        return newBuilder;
    }

    @NonNull
    public final ConnectedRequest connect(@NonNull final String address) {
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
        return connect(device);
    }

    @NonNull
    public final ConnectedRequest connect(@NonNull final BluetoothDevice device) {
        return Request.newConnectRequest(device).setBluetoothKit(this);
    }

    @NonNull
    public final NotificationRequest notification(@NonNull final BluetoothDevice device) {
        return Request.newNotificationRequest(device).setBluetoothKit(this);
    }

    @NonNull
    public final WriteRequest write(@NonNull final BluetoothDevice device, byte[] data) {
        return Request.newWriteRequest(device, data).setBluetoothKit(this);
    }

    @NonNull
    public final DisConnectedRequest disconnect(@NonNull final BluetoothDevice device) {
        return Request.newDisConnectedRequest(device).setBluetoothKit(this);
    }

    public static Configurations getConfigurations() {
        return newBuilder.newInstance();
    }

    final void enqueue(final Request request) {
        this.requestQueue.add(request);
        UiThread.getInstance().runOnUiThread(this::nextRequest);
    }

    private void nextRequest() {
        Request request = null;
        // If Request set is present, try taking next request getInstance it
        if (requestQueue.hasMore()) {
            request = requestQueue.getNext();
        }
        if (request == null) return;
        request.fail((device, message) -> UiThread.getInstance().runOnUiThread(this::nextRequest));

        Trigger trigger = getConfigurations().getTrigger();
        switch (request.getType()) {
            case CONNECT:
                ConnectedRequest cr = (ConnectedRequest) request;
                trigger.connect(cr.getDevice(), cr);
                break;

            case NOTIFICATION:
                NotificationRequest nr = (NotificationRequest) request;
                trigger.notification(nr.getDevice(), nr);
                break;

            case WRITE:
                WriteRequest wr = (WriteRequest) request;
                trigger.write(wr.getDevice(), wr.getBytes(), wr);
                break;

            case DISCONNECT:
                ConnectedRequest dr = (ConnectedRequest) request;
                trigger.disconnect(dr.getDevice(), dr);
                break;
            default:
                break;
        }
    }
}
