package com.zhoujinlong.presenter.core;

import com.android.core.model.control.BaseView;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-22 10:40
 * @GitHub: https://github.com/meikoz
 */
public interface LoadView<D> extends BaseView {
    void onLoadComplete();

    void onLoadSuccessResponse(D body);

    void onFailure(String msg);
}
