package com.meikoz.basic.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.meikoz.basic.R;
import com.meikoz.basic.ui.fragment.NetworkFragment;
import com.meikoz.basic.ui.fragment.SweetAlertDialogFragment;
import com.meikoz.core.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        getFragmentManager().beginTransaction().replace(R.id.container, new NetworkFragment()).commit();
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
            case R.id.action_network_sample:
                fragmentTransaction.replace(R.id.container, new NetworkFragment()).commit();
                break;

            case R.id.action_sweetalert_sample:
                mSweetAlertDialogFragment = new SweetAlertDialogFragment();
                fragmentTransaction.replace(R.id.container, mSweetAlertDialogFragment).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
