package com.meikoz.basic.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.meikoz.basic.R;
import com.meikoz.basic.ui.fragment.SweetAlertDialogFragment;
import com.meikoz.core.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        getFragmentManager().beginTransaction().replace(R.id.container, new SweetAlertDialogFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SweetAlertDialogFragment mSweetAlertDialogFragment;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_horizontal_stepview:
                mSweetAlertDialogFragment = new SweetAlertDialogFragment();
                fragmentTransaction.replace(R.id.container, mSweetAlertDialogFragment).commit();
                break;

            case R.id.action_drawcanvas:
                mSweetAlertDialogFragment = new SweetAlertDialogFragment();
                fragmentTransaction.replace(R.id.container, mSweetAlertDialogFragment).commit();
                break;
            case R.id.action_vertical_reverse:
                mSweetAlertDialogFragment = new SweetAlertDialogFragment();
                fragmentTransaction.replace(R.id.container, mSweetAlertDialogFragment).commit();
                break;

            case R.id.action_vertical_forward:
                mSweetAlertDialogFragment = new SweetAlertDialogFragment();
                fragmentTransaction.replace(R.id.container, mSweetAlertDialogFragment).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
