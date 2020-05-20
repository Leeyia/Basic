package com.racofix.things2.example;

import android.widget.Toast;

import com.racofix.things2.example.mvp.Register;
import com.racofix.things2.example.mvp.RegisterI;
import com.racofix.things2.mvp.BaseLogicActivity;
import com.racofix.things2.mvp.LogicArr;

@LogicArr(Register.class)
public class RegisterActivity extends BaseLogicActivity
        implements RegisterI.View {

    @Override
    public void sign_up_success() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
}
