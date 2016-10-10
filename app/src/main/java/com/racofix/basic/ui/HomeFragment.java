package com.racofix.basic.ui;

import android.os.Bundle;

import com.android.core.base.BaseFragment;
import com.racofix.basic.R;

import butterknife.OnClick;

/**
 * @User: 蜡笔小新
 * @date: 16-10-10
 * @GitHub: https://github.com/meikoz
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected Class getLogic() {
        return null;
    }

    @OnClick(R.id.tv_jump_login)
    void onJump2Login() {
        LoginActivity.jumpTo(getActivity());
    }

}
