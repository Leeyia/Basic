package com.android.core.base.rx;

import com.android.core.base.BaseView;

public interface RxView<T> extends BaseView {

    void onReceiveData2Api(T t, boolean isMore);
}
