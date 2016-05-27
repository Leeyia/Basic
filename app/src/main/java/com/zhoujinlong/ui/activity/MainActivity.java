package com.zhoujinlong.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.core.widget.TabStripView;
import com.zhoujinlong.R;
import com.zhoujinlong.ui.fragment.CompeteFragment;
import com.zhoujinlong.ui.fragment.DiscoveryFragment;
import com.zhoujinlong.ui.fragment.PersonFragment;

public class MainActivity extends AppCompatActivity {

    private TabStripView navigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        navigateTabBar.addTab(CompeteFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_bar_home, R.drawable.ic_tab_bar_home_selected, R.string.abc_tab_text_home));
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
