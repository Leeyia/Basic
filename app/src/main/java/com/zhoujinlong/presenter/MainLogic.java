package com.zhoujinlong.presenter;

import com.android.core.model.annotation.Implement;
import com.zhoujinlong.presenter.impl.MainLogicImpl;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 12:03
 * @GitHub: https://github.com/meikoz
 */
@Implement(MainLogicImpl.class)
public interface MainLogic {
    void onLoadRemoteData(boolean isMore, int page);
}
