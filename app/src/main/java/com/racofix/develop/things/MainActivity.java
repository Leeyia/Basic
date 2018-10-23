package com.racofix.develop.things;

import android.os.Bundle;

import com.racofix.develop.app.BaseActivity;
import com.racofix.develop.http.HttpManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpManager httpManager = new HttpManager();
    }
}
