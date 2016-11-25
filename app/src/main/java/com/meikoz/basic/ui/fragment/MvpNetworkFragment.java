package com.meikoz.basic.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meikoz.basic.R;
import com.meikoz.basic.adapter.NetworkAdapter;
import com.meikoz.basic.api.ApiInterface;
import com.meikoz.basic.api.Config;
import com.meikoz.basic.bean.Gank;
import com.meikoz.basic.presenter.NetworkLogicI;
import com.meikoz.basic.presenter.NetworkLogicImpl;
import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.ui.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @User: 蜡笔小新
 * @date: 16-11-25
 * @GitHub: https://github.com/meikoz
 */

public class MvpNetworkFragment extends BaseFragment implements XRecyclerView.LoadingListener, NetworkLogicI.NetworkView {

    @Bind(R.id.network_recycler_view)
    XRecyclerView mRecyclerView;
    private List<Gank.ResultsBean> mNetworkDatas = new ArrayList<>();
    private NetworkAdapter mAdapter;
    private int page = Config.page;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_content_network;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mAdapter = new NetworkAdapter(getActivity(), R.layout.item_content_home, mNetworkDatas);

        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.abc_icon_down_arrow);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected Class getLogicClazz() {
        return NetworkLogicI.class;
    }

    @Override
    protected void onInitData2Remote() {
        super.onInitData2Remote();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ((NetworkLogicImpl) mPresenter).onLoadNetworkData(Config.size, Config.page);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((NetworkLogicImpl) mPresenter).onLoadNetworkData(Config.size, page);
    }

    private void onLoadComplete(int page) {
        if (page == 0) {
            mNetworkDatas.clear();
            mRecyclerView.refreshComplete();
        } else
            mRecyclerView.loadMoreComplete();
    }

    @Override
    public void onResponse(Gank responce) {
        onLoadComplete(page);
        List<Gank.ResultsBean> results = responce.getResults();
        mNetworkDatas.addAll(results);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        onLoadComplete(page);
        new SweetAlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new SweetAlertDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {

                    }
                })
                .show();
    }
}
