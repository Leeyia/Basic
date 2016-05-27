package com.zhoujinlong.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.core.ui.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;
import com.zhoujinlong.R;
import com.zhoujinlong.model.bean.Classify;
import com.zhoujinlong.ui.view.CompeteView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author meikoz on 2016/3/30.
 * email  meikoz@126.com
 */
public class CompeteFragment extends BaseFragment implements CompeteView {

    @Bind(R.id.recly_view)
    XRecyclerView reclyView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_compete;
    }

    @Override
    protected void onInitView() {
        RecyclerView.Adapter adapter = new RecyclerAdapter(getActivity(), R.layout.item_home_list) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, Object item) {

            }
        };
        reclyView.setAdapter(adapter);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    public void onSuccess(List<Classify.TngouEntity> list) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
