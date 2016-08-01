package com.racofix.presenter;

import com.android.core.model.annotation.Implement;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 12:03
 * @GitHub: https://github.com/meikoz
 */
@Implement(MainPresenter.class)
public interface MainContract {
    void onLoadRemoteData(boolean isMore, int page);
}
