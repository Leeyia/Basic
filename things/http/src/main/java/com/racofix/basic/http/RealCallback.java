package com.racofix.basic.http;

public interface RealCallback<T> {
    void successful(T body);

    void failure(String message);
}
