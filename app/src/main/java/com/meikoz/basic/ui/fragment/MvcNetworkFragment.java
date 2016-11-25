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
import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.ui.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @User: 蜡笔小新
 * @date: 16-11-25
 * @GitHub: https://github.com/meikoz
 */

public class MvcNetworkFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    @Bind(R.id.network_recycler_view)
    XRecyclerView mRecyclerView;
    private List<Gank.ResultsBean> mNetworkDatas = new ArrayList<>();
    private NetworkAdapter mAdapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onLoadNetworkData(Config.size, Config.page);
    }

    private void onLoadNetworkData(int size, final int page) {
        ApiInterface.ApiFactory.createApi().onLoadNetworkData(size, page).enqueue(new Callback<Gank>() {
            @Override
            public void onResponse(Call<Gank> call, Response<Gank> response) {
                if (!response.body().isError()) {
                    onLoadComplete(page);
                    if (page == 0)
                        mNetworkDatas.clear();
                    List<Gank.ResultsBean> results = response.body().getResults();
                    mNetworkDatas.addAll(results);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Gank> call, Throwable t) {
                onLoadComplete(page);
                new SweetAlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("确定", new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onRefresh() {
        onLoadNetworkData(Config.size, Config.page);
    }

    @Override
    public void onLoadMore() {
        int page = Config.page;
        page++;
        onLoadNetworkData(Config.size, page);
    }

    private void onLoadComplete(int page) {
        if (page == 0)
            mRecyclerView.refreshComplete();
        else
            mRecyclerView.loadMoreComplete();
    }
}
