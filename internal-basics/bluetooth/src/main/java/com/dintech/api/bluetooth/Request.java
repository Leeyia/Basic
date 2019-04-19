package com.dintech.api.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

import com.dintech.api.bluetooth.callback.DisConnectionCallback;
import com.dintech.api.bluetooth.callback.FailCallback;
import com.dintech.api.bluetooth.callback.NotificationCallback;
import com.dintech.api.bluetooth.callback.SuccessCallback;

public abstract class Request {

    public enum Type {
        CONNECT,
        WRITE,
        DISCONNECT,
        NOTIFICATION
    }

    private final Type type;
    private final BluetoothDevice device;

    FailCallback failCallback;
    SuccessCallback successCallback;
    NotificationCallback notificationCallback;
    DisConnectionCallback disConnectionCallback;
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
    @NonNull
    Request setBluetoothKit(@NonNull final BluetoothKit manager) {
        this.manager = manager;
        return this;
    }

    /**
     * Creates a new connect request. This allows to set a callback to the connect event,
     * just like any other request.
     *
     * @param device the device to connect to.
     * @return The new connect request.
     */
    @NonNull
    static ConnectRequest connect(@NonNull final BluetoothDevice device) {
        return new ConnectRequest(Type.CONNECT, device);
    }

    @NonNull
    static NotificationRequest notify(@NonNull final BluetoothDevice device) {
        return new NotificationRequest(Type.NOTIFICATION, device);
    }

    @NonNull
    static WriteRequest write(@NonNull final BluetoothDevice device, @NonNull byte[] data) {
        return new WriteRequest(Type.WRITE, device, data);
    }

    @NonNull
    static ConnectRequest disconnet(@NonNull final BluetoothDevice device) {
        return new ConnectRequest(Type.DISCONNECT, device);
    }

    @NonNull
    protected Request done(@NonNull final SuccessCallback callback) {
        this.successCallback = callback;
        return this;
    }

    @NonNull
    protected Request fail(@NonNull final FailCallback callback) {
        this.failCallback = callback;
        return this;
    }

    @NonNull
    protected Request notify(@NonNull final NotificationCallback callback) {
        this.notificationCallback = callback;
        return this;
    }

    @NonNull
    protected Request discall(@NonNull final DisConnectionCallback callback) {
        this.disConnectionCallback = callback;
        return this;
    }

    public void enqueue() {
        manager.enqueue(this);
    }

    public void notifySuccess(@NonNull final BluetoothDevice device) {
        if (successCallback != null)
            successCallback.onSuccessful(device);
    }

    public void notifyFail(@NonNull final BluetoothDevice device, final String message) {
        if (failCallback != null)
            failCallback.onRequestFailed(device, message);
    }

    public void notifyNotification(@NonNull final BluetoothDevice device, byte[] data) {
        if (notificationCallback != null)
            notificationCallback.onNotificationChanged(device, data);
    }

    public void notifyDisConnected(@NonNull final BluetoothDevice device) {
        if (disConnectionCallback != null)
            disConnectionCallback.onDisConnected(device);
    }

    public Type getType() {
        return type;
    }

    public BluetoothDevice getDevice() {
        return device;
    }
}
