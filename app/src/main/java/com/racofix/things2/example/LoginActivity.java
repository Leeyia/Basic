package com.racofix.things2.example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.racofix.things2.example.mvp.Login;
import com.racofix.things2.example.mvp.LoginI;
import com.racofix.things2.example.mvp.Register;
import com.racofix.things2.example.mvp.RegisterI;
import com.racofix.things2.mvp.BaseLogicActivity;
import com.racofix.things2.mvp.LogicArr;

@LogicArr({Register.class, Login.class})
public class LoginActivity extends BaseLogicActivity implements
        LoginI.View, RegisterI.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLogic(Login.class).sign_in("用户名", "密码");
    }

    @Override
    public void sign_in_success() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sign_up_success() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
}
