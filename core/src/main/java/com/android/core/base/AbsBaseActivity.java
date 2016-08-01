package com.android.core.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.android.core.control.ToastUtil;
import com.android.core.control.logcat.Logcat;
import com.android.core.model.LogicProxy;
import com.android.core.widget.dialog.DialogManager;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * @author: 蜡笔小新
 * @date: 2016-05-26 17:17
 * @GitHub: https://github.com/meikoz
 */
public abstract class AbsBaseActivity extends Activity implements BaseView {

    protected Context mContext = null;//context
    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logcat.d("Activity Location (%s.java:0)", getClass().getSimpleName());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        onInitView();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        // 打开Activity动画
    }

    //获得该页面的实例
    public <T> T getLogicImpl(Class cls, BaseView o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    @Override
    public void finish() {
        super.finish();
        // 关闭动画
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.show(msg);
    }

    public void showProgress(String message) {
        DialogManager.showProgressDialog(mContext, message);
    }

    @Override
    public void showProgress(String message, int progress) {
        DialogManager.showProgressDialog(mContext, message, progress);
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
