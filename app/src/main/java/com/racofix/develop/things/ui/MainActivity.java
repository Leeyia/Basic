package com.racofix.develop.things.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.develop.http.ApiService;
import com.racofix.develop.http.CityInfo;
import com.racofix.develop.http.RealCallback;
import com.racofix.develop.http.model.HttpManager;
import com.racofix.develop.mvp.BaseActivity;
import com.racofix.develop.mvp.HttpVo;
import com.racofix.develop.things.R;

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
