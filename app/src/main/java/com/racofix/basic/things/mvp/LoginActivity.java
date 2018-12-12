package com.racofix.basic.things.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.racofix.basic.image.GlideImageEngine;
import com.racofix.basic.image.ImageConfigure;
import com.racofix.basic.image.ImageLoader;
import com.racofix.basic.image.PicassoImageEngine;
import com.racofix.basic.mvp.BaseActivity;
import com.racofix.basic.mvp.annotation.Implement;
import com.racofix.basic.things.R;

@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(view -> getLogicImpl().login("admin", "admin"));

        ImageView target = findViewById(R.id.iv_avatar);

        ImageLoader
                .getDefalut()
                .engine(new GlideImageEngine())
                .display(new ImageConfigure.Configure()
                        .imageUrl("https://avatars2.githubusercontent.com/u/16660064?s=460&v=4")
                        .into(target)
                        .build());
    }

    @Override
    public void successful(String message) {
    }

    @Override
    public void failed(String failMsg) {
    }
}
