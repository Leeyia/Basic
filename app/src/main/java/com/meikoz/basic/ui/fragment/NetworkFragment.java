package com.meikoz.basic.ui.fragment;

import android.os.Bundle;

import com.meikoz.basic.R;
import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.ui.TabStripView;

import butterknife.Bind;

/**
 * @User: 蜡笔小新
 * @date: 16-11-25
 * @GitHub: https://github.com/meikoz
 */

public class NetworkFragment extends BaseFragment {

    @Bind(R.id.navigateTabBar)
    TabStripView navigateTabBar;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_net_work;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);
        navigateTabBar.addTab(MvcNetworkFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_strip_icon_feed, R.mipmap.ic_tab_strip_icon_feed_selected, R.string.abc_tab_text_home));
        navigateTabBar.addTab(MvpNetworkFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_strip_icon_category, R.mipmap.ic_tab_strip_icon_category_selected, R.string.abc_tab_text_find));
        navigateTabBar.addTab(RxNetworkFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_strip_icon_pgc, R.mipmap.ic_tab_strip_icon_pgc_selected, R.string.abc_tab_text_feed));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        navigateTabBar.onSaveInstanceState(outState);
    }
}
