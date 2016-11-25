package com.meikoz.basic.presenter;

import com.meikoz.basic.bean.Gank;
import com.meikoz.core.base.BaseView;
import com.meikoz.core.model.annotation.Implement;

@Implement(NetworkLogicImpl.class)
public interface NetworkLogicI {

    void onLoadNetworkData(int size, final int page);

    interface NetworkView extends BaseView {
        void onResponse(Gank responce);

        void onFailure(String msg);
    }
}
