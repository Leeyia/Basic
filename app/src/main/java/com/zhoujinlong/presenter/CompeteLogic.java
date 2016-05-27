package com.zhoujinlong.presenter;

import com.android.core.model.annotation.Implement;
import com.android.core.model.control.BaseLogic;
import com.zhoujinlong.presenter.impl.CompeteLogicImpl;
import com.zhoujinlong.ui.view.CompeteView;

/**
 * author meikoz on 2016/4/27.
 * email  meikoz@126.com
 */
@Implement(CompeteLogicImpl.class)
public interface CompeteLogic extends BaseLogic<CompeteView> {

    void onRefreshData();

    void onLoadMoreData();

}
