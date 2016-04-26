package com.matto.presenter;

import com.common.model.annotation.Implement;
import com.common.model.control.MvpLogic;
import com.matto.presenter.impl.MainLogicImpl;
import com.matto.ui.view.MainView;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
@Implement(MainLogicImpl.class)
public interface MainLogic extends MvpLogic<MainView> {

    void switchNavigation(int id);

}
