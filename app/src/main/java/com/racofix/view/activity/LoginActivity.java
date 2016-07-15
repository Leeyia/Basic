package com.racofix.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.android.core.control.Toast;
import com.racofix.presenter.core.LoadView;
import com.android.core.ui.BaseActivity;
import com.racofix.R;
import com.racofix.model.pojo.Classify;
import com.racofix.presenter.ILogin;
import com.racofix.presenter.LoginLogic;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 10:51
 * @GitHub: https://github.com/meikoz
 */
public class LoginActivity extends BaseActivity implements LoadView<Classify> {

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
    com.racofix.view.widget.TitleBar titlebar;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {
        titlebar.setTitle("登录页面");
        mPresenter = getLogicImpl(ILogin.class, this);
        ((LoginLogic) mPresenter).login("zhangsan", "123");
    }

    @OnClick(R.id.btn_login)
    void login() {
        showLoadView();

    }

    @Override
    public void onFailure(String msg) {
        hideLoadView();
        Toast.show(msg);
    }

    @Override
    public void onLoadComplete() {
        hideLoadView();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onLoadSuccessResponse(Classify body) {
//        Toast.show(body.getTngou().toString());
    }
}
