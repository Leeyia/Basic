package com.meikoz.basic;

import com.meikoz.core.model.annotation.Implement;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */
@Implement(MainLogicImpl.class)
public interface MainLogicI {
    void onLoadData2Remote();
}
