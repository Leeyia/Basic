package com.racofix.presenter;

import com.android.core.api.TransformUtils;
import com.android.core.presenter.DataLayerLogicImpl;
import com.android.core.base.rx.RxSubscriber;
import com.racofix.api.ApiFactory;
import com.racofix.model.repo.Classify;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 15:15
 * @GitHub: https://github.com/meikoz
 */
public class LoginLogicImpl extends DataLayerLogicImpl<Classify> implements LoginLogicI {

    @Override
    public void onLogin(String username, String password) {
        ApiFactory.createApi().onHome2Api(1)
                .compose(TransformUtils.<Classify>defaultSchedulers())
                .subscribe(new RxSubscriber<Classify>(getView()) {
                    @Override
                    protected void onResponse(Classify classify) {
                        onDataStore2View(classify);
                    }
                });
    }
}
