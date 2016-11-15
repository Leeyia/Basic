package com.meikoz.basic.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meikoz.basic.R;
import com.meikoz.basic.presenter.MainLogicI;
import com.meikoz.basic.presenter.MainLogicImpl;
import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.manage.log.Logger;

import butterknife.Bind;

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

}
