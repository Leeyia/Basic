package com.meikoz.core.base.rx;

import com.meikoz.core.base.BaseView;

public interface RxView<T> extends BaseView {

    void onReceiveData2Api(T t, boolean isMore);
}
