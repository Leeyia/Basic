package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;
import android.os.Looper;

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
    private Blueteeth manager;

    Request(Type type, BluetoothDevice device) {
        this.type = type;
        this.device = device;
    }

    /**
     * Sets the {@link Blueteeth} instance.
     *
     * @param manager the manager in which the request will be executed.
     */

    Request setManager(final Blueteeth manager) {
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

    static WritedRequest newWriteRequest(final BluetoothDevice device, byte[] data) {
        return new WritedRequest(Type.WRITE, device, data);
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

    /**
     * Asserts that the synchronous method was not called from the UI thread.
     *
     * @throws IllegalStateException when called from a UI thread.
     */
    public void assertNotMainThread() throws IllegalStateException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot execute synchronous operation from the UI thread.");
        }
    }
}
