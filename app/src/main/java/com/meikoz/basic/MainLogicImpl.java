package com.meikoz.basic;

import android.widget.Toast;

import com.meikoz.core.MainApplication;
import com.meikoz.core.base.BasePresenter;
import com.meikoz.core.base.BaseView;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class MainLogicImpl extends BasePresenter<BaseView> implements MainLogicI {
    @Override
    public void onLoadData2Remote() {
        Toast.makeText(MainApplication.getContext(), "onLoadData2Remote", Toast.LENGTH_SHORT).show();
    }
}
