package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.ReceiveCallback;
import com.dintech.api.bleep.callback.SuccessCallback;
import com.dintech.api.bleep.exception.BleException;

public abstract class Request {

    public enum Type {
        CONNECT,
        WRITE,
        DISCONNECT,
        NOTIFICATION
    }

    private final Type type;
    private final BluetoothDevice device;

    private FailCallback failCallback;
    private SuccessCallback successCallback;
    private ReceiveCallback receiveCallback;
    private BluetoothKit manager;

    public Request(Type type, BluetoothDevice device) {
        this.type = type;
        this.device = device;
    }

    /**
     * Sets the {@link BluetoothKit} instance.
     *
     * @param manager the manager in which the request will be executed.
     */

    Request setBluetoothKit(final BluetoothKit manager) {
        this.manager = manager;
        return this;
    }

    /**
     * Creates a new newConnectRequest request. This allows to set a callback to the newConnectRequest event,
     * just like any other request.
     *
     * @param device the device to newConnectRequest to.
     * @return The new newConnectRequest request.
     */

    static ConnectedRequest newConnectRequest(final BluetoothDevice device) {
        return new ConnectedRequest(Type.CONNECT, device);
    }

    static NotificationRequest newNotificationRequest(final BluetoothDevice device) {
        return new NotificationRequest(Type.NOTIFICATION, device);
    }

    static WriteRequest newWriteRequest(final BluetoothDevice device, byte[] data) {
        return new WriteRequest(Type.WRITE, device, data);
    }

    static DisConnectedRequest newDisConnectedRequest(final BluetoothDevice device) {
        return new DisConnectedRequest(Type.DISCONNECT, device);
    }


    Request done(final SuccessCallback callback) {
        this.successCallback = callback;
        return this;
    }

    Request fail(final FailCallback callback) {
        this.failCallback = callback;
        return this;
    }

    Request receive(final ReceiveCallback callback) {
        this.receiveCallback = callback;
        return this;
    }

    public void enqueue() {
        manager.enqueue(this);
    }

    public void notifySuccess(final BluetoothDevice device) {
        if (successCallback != null)
            successCallback.onSuccessful(device);
    }

    public void notifyFail(final BluetoothDevice device, final BleException e) {
        if (failCallback != null)
            failCallback.onRequestFailed(device, e);
    }

    public void notifyNotification(final BluetoothDevice device, byte[] bytes) {
        if (receiveCallback != null)
            receiveCallback.onRequestReceive(device, bytes);
    }

    public Type getType() {
        return type;
    }

    public BluetoothDevice getDevice() {
        return device;
    }
}
