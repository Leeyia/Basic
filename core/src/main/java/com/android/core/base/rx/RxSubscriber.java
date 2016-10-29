package com.android.core.base.rx;

import com.android.core.MainApplication;
import com.android.core.base.BaseView;
import com.android.core.manage.NetWorkHelper;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public abstract class RxSubscriber<D> extends Subscriber<D> {

    public BaseView mView;

    public RxSubscriber(BaseView view) {
        this.mView = view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!NetWorkHelper.isNetConnected(MainApplication.getContext())) {
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            _onError("网络不可用");
        }
    }

    @Override
    public void onCompleted() {
//        if (mView != null)
//            mView.hideProgress();
    }

    @Override
    public void onNext(D d) {
        onResponse(d);
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetWorkHelper.isNetConnected(MainApplication.getContext())) {
            _onError("网络不可以用");
        } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            _onError("请求超时，请稍后再试..");
        } else {
            _onError("请求失败，请稍后再试..");
        }
    }

    /**
     * 进行统一处理fail
     */
    protected void _onError(String message) {
//        if (mView != null)
//            mView.showErrorMessage("网络问题", message);
    }

    /**
     * 同意返回数据
     */
    protected abstract void onResponse(D d);
}
