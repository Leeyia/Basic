package com.zhoujinlong.presenter.core;

import com.android.core.model.mvp.BaseView;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 11:47
 * @GitHub: https://github.com/meikoz
 */
public interface BaseListView<T> extends BaseView {

    void onLoadComplete(boolean isMore);

    void onResponseLData(T response, boolean isMore);
}
