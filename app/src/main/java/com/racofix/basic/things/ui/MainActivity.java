package com.racofix.basic.things.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.basic.http.ApiService;
import com.racofix.basic.http.CityInfo;
import com.racofix.basic.http.RealCallback;
import com.racofix.basic.http.model.HttpManager;
import com.racofix.basic.mvp.BaseActivity;
import com.racofix.basic.mvp.HttpVo;
import com.racofix.basic.things.R;

public class MainActivity extends BaseActivity<LoginLogic> implements HttpVo<String> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpManager.newBuilder().create(ApiService.class).getCityCall().enqueue(new RealCallback<CityInfo>() {
            @Override
            public void successful(CityInfo body) {
                CityInfo body1 = body;
            }

            @Override
            public void failure(String message) {
                String message1 = message;
            }
        });
    }

    @Override
    public void onResponse(String s) {

    }

    @Override
    public void onFailure(String message) {

    }
}
