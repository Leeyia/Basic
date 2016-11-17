package com.meikoz.basic.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.meikoz.basic.R;
import com.meikoz.basic.presenter.MainLogicI;
import com.meikoz.core.base.BaseActivity;
import com.meikoz.core.ui.CustomDialog;

public class MainActivity extends BaseActivity implements MainLogicI.MainView {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        new CustomDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("您确定要删除dwadwa么?")
                .setCancelable(false)
                .setPositiveButton("ceshi", new CustomDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        Toast.makeText(MainActivity.this, "确定操作" + which, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected Class getLogicClazz() {
        return MainLogicI.class;
    }

    @Override
    protected void onInitData2Remote() {
        super.onInitData2Remote();
    }

    @Override
    public void onLoadSuccessHandler(String responce) {

    }
}
