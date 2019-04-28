package com.dintech.api.bleep;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue {


    private final Queue<Request> requests;

    public RequestQueue() {
        this.requests = new LinkedList<>();
    }


    public RequestQueue add( final Request request) {
        requests.add(request);
        return this;
    }

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

    public Request getNext() {
        try {
            return requests.remove();
        } catch (final Exception e) {
            return null;
        }
    }

    public boolean hasMore() {
        return !requests.isEmpty();
    }
}
