package com.android.core.base.rx;

import com.android.core.base.BaseView;

/**
 * Created by zjl on 16-9-18.
 */
public interface RxView<T> extends BaseView {

    void onReceiveData2Api(T t, boolean isMore);
}
