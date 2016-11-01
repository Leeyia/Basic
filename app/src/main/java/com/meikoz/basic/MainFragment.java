package com.meikoz.basic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.manage.log.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class MainFragment extends BaseFragment {

    @Bind(R.id.button)
    Button mButton;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("MainFragment", mPresenter.getClass().getSimpleName());
                ((MainLogicImpl) mPresenter).onLoadData2Remote();
            }
        });
    }

    @Override
    protected Class getLogicClazz() {
        return MainLogicI.class;
    }

    @Override
    protected void onInitData2Api() {
        super.onInitData2Api();
    }
}
