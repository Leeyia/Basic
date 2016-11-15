package com.meikoz.basic.presenter;

import com.meikoz.core.base.BaseView;
import com.meikoz.core.model.annotation.Implement;

@Implement(MainLogicImpl.class)
public interface MainLogicI {
    void onLoadData2Remote();

    interface MainView extends BaseView{
        void onLoadSuccessHandler(String responce);
    }
}
