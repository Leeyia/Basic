package com.racofix.presenter;

import com.android.core.api.TransformUtils;
import com.android.core.base.rx.RxSubscriber;
import com.android.core.presenter.DataLayerLogicImpl;
import com.racofix.api.ApiFactory;
import com.racofix.model.repo.Classify;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 12:05
 * @GitHub: https://github.com/meikoz
 */
public class MainLogicImpl extends DataLayerLogicImpl<Classify> implements MainLogicI {
    @Override
    public void onLoadRemoteData(final boolean isMore, int page) {
        /**
         * rx sample
         */
        ApiFactory.createApi().onHomeData2Api(page)
                .compose(TransformUtils.<Classify>defaultSchedulers())
                .subscribe(new RxSubscriber<Classify>(getView()) {
                    @Override
                    protected void onResponse(Classify classify) {
                        onDataStore2View(classify, isMore);
                    }
                });

        /**
         * old sample
         */
//        ApiFactory.createApi().getImageClassify(page).enqueue(new Callback<Classify>() {
//            @Override
//            public void onResponse(Call<Classify> call, Response<Classify> response) {
//                onLoadComplete(response, isMore);
//            }
//
//            @Override
//            public void onFailure(Call<Classify> call, Throwable t) {
//                onFailer(t.getLocalizedMessage());
//            }
//        });

    }
}
