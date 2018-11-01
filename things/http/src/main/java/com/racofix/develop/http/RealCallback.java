package com.racofix.develop.http;

public interface RealCallback<T> {
    void successful(T body);

    void failure(String message);
}
