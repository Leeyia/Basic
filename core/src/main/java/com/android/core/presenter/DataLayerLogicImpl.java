package com.android.core.presenter;

import com.android.core.base.BasePresenter;
import com.android.core.base.rx.RxView;

/**
 * 统一处理公共数据类
 */
public class DataLayerLogicImpl<T> extends BasePresenter<RxView<T>> implements DataLayerLogicI<T> {

    @Override
    public void onDataStore2View(T t, boolean isMore) {
        getView().onReceiveData2Api(t, isMore);
    }

    @Override
    public void onDataStore2View(T t) {
        getView().onReceiveData2Api(t, false);
    }
}
