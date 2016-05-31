package com.zhoujinlong.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.android.core.model.control.LogicProxy;
import com.android.core.ui.BaseActivity;
import com.zhoujinlong.ui.widget.TitleBar;
import com.zhoujinlong.R;
import com.zhoujinlong.presenter.LoginLogic;
import com.zhoujinlong.ui.view.LoginView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public class LoginActivity extends BaseActivity implements LoginView {

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
    TitleBar titlebar;

    LoginLogic mLoginLogic;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {
        titlebar.setTitle("登录页面");
        mLoginLogic = getLogicImpl(LoginLogic.class, this);
    }

    @Override
    protected void onInitData() {

    }

    @OnClick(R.id.btn_login)
    void login() {
        mLoginLogic.login("zhangsan", "123");
        startActivity(new Intent(LoginActivity.this, SwipBackActivity.class));
    }

    @Override
    public void onLoginSuccess() {
    }

    @Override
    public void onLoginFail() {
    }
}
