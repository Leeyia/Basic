package com.racofix.basic.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.core.base.BaseActivity;
import com.racofix.basic.R;
import com.racofix.basic.presenter.LoginLogicI;
import com.racofix.basic.presenter.LoginLogicImpl;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @User: 蜡笔小新
 * @date: 16-10-10
 * @GitHub: https://github.com/meikoz
 */

public class LoginActivity extends BaseActivity implements LoginLogicI.LoginView {

    public static void jumpTo(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }
    @Override
    protected Class getLogic() {
        return LoginLogicI.class;
    }

    @OnClick(R.id.btn_user_login)
    void onUserLogin(){
        ((LoginLogicImpl) mPresenter).userLogin("zhangsan", "123456");
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

}
