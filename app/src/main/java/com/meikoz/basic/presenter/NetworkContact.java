package com.meikoz.basic.presenter;

import com.meikoz.basic.model.Gank;
import com.meikoz.core.base.BaseView;
import com.meikoz.core.model.annotation.Implement;

@Implement(NetworkLogicImpl.class)
public interface NetworkContact {

    void onLoadNetworkData(int size, final int page);

    interface NetworkView extends BaseView {
        void onResponse(Gank responce);

        void onFailure(String msg);
    }
}
