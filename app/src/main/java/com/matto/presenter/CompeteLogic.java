package com.matto.presenter;

import com.common.model.annotation.Implement;
import com.common.model.control.MvpLogic;
import com.matto.presenter.impl.CompeteLogicImpl;
import com.matto.ui.view.CompeteView;

/**
 * author meikoz on 2016/4/27.
 * email  meikoz@126.com
 */
@Implement(CompeteLogicImpl.class)
public interface CompeteLogic extends MvpLogic<CompeteView> {

    void onRefreshData();

    void onLoadMoreData();

}
