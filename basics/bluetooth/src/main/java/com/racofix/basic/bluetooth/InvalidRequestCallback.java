package com.racofix.basic.bluetooth;

public interface InvalidRequestCallback {

    /**
     * A callback invoked when the request was invalid, for example when was called before the
     * device was connected.
     */
    void onInvalidRequest();
}
