package com.racofix.basic.app;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T> extends AppCompatActivity {

    protected abstract void init();
}
