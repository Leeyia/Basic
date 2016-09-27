package com.android.core.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.core.control.ToastUtil;
import com.android.core.control.logcat.Logcat;
import com.android.core.presenter.LogicProxy;
import com.android.core.widget.dialog.DialogManager;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-26 17:19
 * @GitHub: https://github.com/meikoz
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected BasePresenter mPresenter;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResource() != 0) {
            return inflater.inflate(getLayoutResource(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logcat.d("Class Name (%s.java:0)", getClass().getSimpleName());
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onInitView();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();


    //获得该页面的实例
    public <T> T getLogicImpl(Class cls, BaseView o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showProgress(String msg) {
        DialogManager.showProgressDialog(mContext, msg);
    }

    @Override
    public void showProgress(String msg, int progress) {
        DialogManager.showProgressDialog(mContext, msg, progress);
    }

    @Override
    public void hideProgress() {
        DialogManager.hideProgressDialog();
    }

    @Override
    public void showErrorMessage(String msg, String content) {
        DialogManager.showErrorDialog(mContext, msg, content, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
    }
}
