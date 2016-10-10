package com.racofix.basic.ui;

import android.os.Bundle;

import com.android.core.base.BaseActivity;
import com.android.core.ui.TabStripView;
import com.racofix.basic.R;

public class MainActivity extends BaseActivity {

    private TabStripView mNavigateTabBar;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mNavigateTabBar = (TabStripView) findViewById(R.id.navigateTabBar);
        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);

        mNavigateTabBar.addTab(HomeFragment.class,
                new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_feed,
                        R.drawable.ic_tab_strip_icon_feed_selected, R.string.tab_bar_text_feed));

        mNavigateTabBar.addTab(HomeFragment.class,
                new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_category,
                        R.drawable.ic_tab_strip_icon_category_selected, R.string.tab_bar_text_category));

        mNavigateTabBar.addTab(HomeFragment.class,
                new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_pgc,
                        R.drawable.ic_tab_strip_icon_pgc_selected, R.string.tab_bar_text_pgc));

        mNavigateTabBar.addTab(HomeFragment.class,
                new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_profile,
                        R.drawable.ic_tab_strip_icon_profile_selected, R.string.tab_bar_text_profile));
    }

    @Override
    protected Class getLogic() {
        return null;
    }
}
