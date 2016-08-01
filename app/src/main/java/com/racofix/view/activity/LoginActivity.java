package com.racofix.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.android.core.base.AbsBaseActivity;
import com.android.core.model.LoadEveryLogic;
import com.racofix.R;
import com.racofix.model.repo.Classify;
import com.racofix.presenter.LoginContract;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 10:51
 * @GitHub: https://github.com/meikoz
 */
public class LoginActivity extends AbsBaseActivity implements LoadEveryLogic.LoadEveryView<Classify> {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Bind(R.id.edit_username)
    EditText mEditName;
    @Bind(R.id.edit_passwrod)
    EditText mEditPasswrod;

    @Bind(R.id.title_bar)
    com.racofix.widget.TitleBar titlebar;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {
        titlebar.setTitle("登录页面");
        mPresenter = getLogicImpl(LoginContract.class, this);
    }

    @OnClick(R.id.btn_login)
    void login() {
        showProgress("正在登陆");
        ((LoginContract) mPresenter).onLogin("zhangsan", "456");
    }

    @Override
    public void onLoadComplete(Classify body) {
        hideProgress();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onLoadFailer(String msg) {
        showErrorMessage("网络错误", msg);
    }
}
