package com.android.core.presenter;

public interface DataLayerLogicI<T> {

    void onDataStore2View(T t, boolean isMore);

    void onDataStore2View(T t);
}
