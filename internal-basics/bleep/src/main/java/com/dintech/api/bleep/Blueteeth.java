package com.dintech.api.bleep;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

public class Blueteeth {

    private RequestQueue mInitRequestQueue;
    private static Blueteeth bluetoothKit;
    private static Configurations.Builder newBuilder;

    private Blueteeth() {
        if (newBuilder == null)
            throw new RuntimeException("Blueteeth Occurred Initialize Exception!");
        this.mInitRequestQueue = new RequestQueue();
    }

    public static Blueteeth getInstance() {
        if (bluetoothKit == null) synchronized (Blueteeth.class) {
            if (bluetoothKit == null) bluetoothKit = new Blueteeth();
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
        return Request.newConnectRequest(device).setManager(this);
    }

    @NonNull
    public final NotificationRequest notification(@NonNull final BluetoothDevice device) {
        return Request.newNotificationRequest(device).setManager(this);
    }

    @NonNull
    public final WritedRequest write(@NonNull final BluetoothDevice device, byte[] data) {
        return Request.newWriteRequest(device, data).setManager(this);
    }

    @NonNull
    public final DisConnectedRequest disconnect(@NonNull final BluetoothDevice device) {
        return Request.newDisConnectedRequest(device).setManager(this);
    }

    public static Configurations getConfigurations() {
        return newBuilder.newInstance();
    }

    // Caller controls Request execution order
    final void enqueue(final Request request) {
        this.mInitRequestQueue.add(request);
        UiThread.getInstance().runOnUiThread(this::nextRequest);
    }

    private void nextRequest() {
        Request mRequest = null;
        // If Request set is present, try taking next request getInstance it
        if (mInitRequestQueue.hasMore()) {
            mRequest = mInitRequestQueue.getNext();
        }

        if (!mInitRequestQueue.hasMore() || mRequest == null) return;

        mRequest.fail((device, message) ->
                UiThread.getInstance().runOnUiThread(this::nextRequest));

        Trigger trigger = getConfigurations().getTrigger();
        switch (mRequest.getType()) {
            case CONNECT:
                ConnectedRequest cr = (ConnectedRequest) mRequest;
                trigger.connect(cr.getDevice(), cr);
                break;

            case NOTIFICATION:
                NotificationRequest nr = (NotificationRequest) mRequest;
                trigger.notification(nr.getDevice(), nr);
                break;

            case WRITE:
                WritedRequest wr = (WritedRequest) mRequest;
                trigger.write(wr.getDevice(), wr.getBytes(), wr);
                break;

            case DISCONNECT:
                ConnectedRequest dr = (ConnectedRequest) mRequest;
                trigger.disconnect(dr.getDevice(), dr);
                break;
            default:
                break;
        }
    }
}
