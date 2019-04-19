package com.dintech.api.bluetooth;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue {

    @NonNull
    private final Queue<Request> requests;

    public RequestQueue() {
        this.requests = new LinkedList<>();
    }

    @NonNull
    public RequestQueue add(@NonNull final Request request) {
        requests.add(request);
        return this;
    }

    @IntRange(from = 0)
    public int size() {
        return requests.size();
    }

    /**
     * Returns whether the set is empty, or not.
     *
     * @return true if the set is empty. Set gets emptied while it all enqueued operations
     * are being executed.
     */
    public boolean isEmpty() {
        return requests.isEmpty();
    }

    public void clearQueue() {
        requests.clear();
    }

    @Nullable
    public Request getNext() {
        try {
            return requests.remove();
            // poll() may also throw an exception
            // See: https://github.com/NordicSemiconductor/Android-BLE-Library/issues/37
        } catch (final Exception e) {
            return null;
        }
    }

    public boolean hasMore() {
        return !requests.isEmpty();
    }
}
