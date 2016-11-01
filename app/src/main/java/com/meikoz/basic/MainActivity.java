package com.meikoz.basic;

import android.os.Bundle;
import android.widget.FrameLayout;
import com.meikoz.core.base.BaseActivity;
import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_content)
    FrameLayout mMainContent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        getFragmentManager().beginTransaction().replace(R.id.main_content, new MainFragment());
    }
}
