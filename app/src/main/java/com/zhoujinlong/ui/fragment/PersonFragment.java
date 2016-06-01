package com.zhoujinlong.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.core.ui.BaseFragment;
import com.android.core.control.StatusBarUtil;
import com.zhoujinlong.R;

import butterknife.Bind;

/**
 * Created by 会写代码的小新 on 2016/3/31.
 */
public class PersonFragment extends BaseFragment {

    @Bind(R.id.notify_sv)
    ScrollView mNotifyNavi;
    @Bind(R.id.ll_navi_top)
    LinearLayout mNavitop;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void onInitView() {
        StatusBarUtil.setColor(getActivity(), R.color.abc_theme_black_7f);
        mNavitop.getBackground().setAlpha(0);

    }

    @Override
    protected void onInitData() {

    }

}
