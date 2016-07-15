package com.racofix.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.android.core.widget.TabStripView;
import com.racofix.R;
import com.racofix.view.fragment.DiscoveryFragment;
import com.racofix.view.fragment.HomeFragment;
import com.racofix.view.fragment.PersonFragment;

public class MainActivity extends FragmentActivity {

    private TabStripView navigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        navigateTabBar = (TabStripView) findViewById(R.id.navigateTabBar);
        //对应xml中的containerId
        navigateTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.abc_tab_text_normal));
        //对应xml中的navigateTabSelectedTextColor
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.colorPrimary));

        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(HomeFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_bar_home, R.drawable.ic_tab_bar_home_selected, R.string.abc_tab_text_home));
        navigateTabBar.addTab(DiscoveryFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_bar_find, R.drawable.ic_tab_bar_find_selected, R.string.abc_tab_text_find));
        navigateTabBar.addTab(PersonFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_bar_person, R.drawable.ic_tab_bar_person_selected, R.string.abc_tab_text_person));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        navigateTabBar.onSaveInstanceState(outState);
    }

}
