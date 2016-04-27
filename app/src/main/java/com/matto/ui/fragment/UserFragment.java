package com.matto.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.common.utils.DisplayUtil;
import com.common.utils.StatusBarUtil;
import com.common.view.base.BaseFragment;
import com.common.view.widget.NotifyScrollView;
import com.matto.R;

import butterknife.Bind;

/**
 * Created by 会写代码的小新 on 2016/3/31.
 */
public class UserFragment extends BaseFragment {

    @Bind(R.id.notify_sv)
    NotifyScrollView mNotifyNavi;
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
        StatusBarUtil.setColor(getActivity(), R.color.material_pink_a200);
        mNavitop.getBackground().setAlpha(0);
        mNotifyNavi.setCallback(new NotifyScrollView.Callback() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                int height = DisplayUtil.dp2px(getContext(), 200);
                float ratio = Math.max(Math.min(1, t / height), 0);
                mNavitop.getBackground().setAlpha((int) ratio * 255);
            }
        });
    }

}
