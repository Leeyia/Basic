package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.callback.DisConnectionCallback;
import com.dintech.api.bleep.callback.FailCallback;
import com.dintech.api.bleep.callback.NotificationCallback;
import com.dintech.api.bleep.callback.SuccessCallback;

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
    
    Request setBluetoothKit( final BluetoothKit manager) {
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
    
    static ConnectRequest connect( final BluetoothDevice device) {
        return new ConnectRequest(Type.CONNECT, device);
    }

    
    static NotificationRequest notify( final BluetoothDevice device) {
        return new NotificationRequest(Type.NOTIFICATION, device);
    }

    
    static WriteRequest write( final BluetoothDevice device,  byte[] data) {
        return new WriteRequest(Type.WRITE, device, data);
    }

    
    static ConnectRequest disconnet( final BluetoothDevice device) {
        return new ConnectRequest(Type.DISCONNECT, device);
    }

    
    protected Request done( final SuccessCallback callback) {
        this.successCallback = callback;
        return this;
    }

    
    protected Request fail( final FailCallback callback) {
        this.failCallback = callback;
        return this;
    }

    
    protected Request notify( final NotificationCallback callback) {
        this.notificationCallback = callback;
        return this;
    }

    
    protected Request discall( final DisConnectionCallback callback) {
        this.disConnectionCallback = callback;
        return this;
    }

    public void enqueue() {
        manager.enqueue(this);
    }

    public void notifySuccess( final BluetoothDevice device) {
        if (successCallback != null)
            successCallback.onSuccessful(device);
    }

    public void notifyFail( final BluetoothDevice device, final String message) {
        if (failCallback != null)
            failCallback.onRequestFailed(device, message);
    }

    public void notifyNotification( final BluetoothDevice device, byte[] data) {
        if (notificationCallback != null)
            notificationCallback.onNotificationChanged(device, data);
    }

    public void notifyDisConnected( final BluetoothDevice device) {
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
