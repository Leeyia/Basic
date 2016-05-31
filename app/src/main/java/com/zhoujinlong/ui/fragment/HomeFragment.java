package com.zhoujinlong.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.core.ui.BaseFragment;
import com.android.core.util.StatusBarUtil;
import com.android.core.widget.CustomViewpager;
import com.android.core.widget.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhoujinlong.R;
import com.zhoujinlong.adapter.CustomViewPageAdapter;
import com.zhoujinlong.adapter.HomeRecyclerAdapter;
import com.android.core.adapter.RecyclerAdapter;
import com.zhoujinlong.model.bean.Classify;
import com.zhoujinlong.presenter.MainLogic;
import com.zhoujinlong.presenter.base.CommonView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 10:51
 * @GitHub: https://github.com/meikoz
 */
public class HomeFragment extends BaseFragment implements CommonView<Classify>, XRecyclerView.LoadingListener {

    @Bind(R.id.recly_view)
    XRecyclerView mRecyclerView;

    private List<Classify.TngouEntity> classifys = new ArrayList<>();
    private CustomViewpager mViewpage;
    private MainLogic mHomeLogic;
    private RecyclerAdapter recyclerAdapter;
    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_compete;
    }

    @Override
    protected void onInitView() {
        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    protected void onInitData() {
        mHomeLogic = getLogicImpl(MainLogic.class, this);
    }

    // 广告数据
    public static List<Integer> getAdData() {
        List<Integer> adList = new ArrayList<>();
        adList.add(R.drawable.abc_adv_1);
        adList.add(R.drawable.abc_adv_2);
        adList.add(R.drawable.abc_adv_3);
        return adList;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //分割线
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(1));
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.abc_icon_down_arrow);

        View header = View.inflate(getActivity(), R.layout.abc_viewpager_view, null);
        mViewpage = (CustomViewpager) header.findViewById(R.id.viewpager);
        mRecyclerView.addHeaderView(header);

        CustomViewPageAdapter adapter = new CustomViewPageAdapter(getActivity(), getAdData());
        mViewpage.updateIndicatorView(getAdData().size(), 0);
        mViewpage.setAdapter(adapter);
        mViewpage.startScorll();

        recyclerAdapter = new HomeRecyclerAdapter(getActivity(), R.layout.item_compete_classitfy, classifys);
        mRecyclerView.setAdapter(recyclerAdapter);
        mRecyclerView.setLoadingListener(this);
        onRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLoadComplete() {
        //加载完成需要做的操作
    }

    /**
     * 网络加载成功后显示数据
     */
    @Override
    public void onShowListData(Classify listData, boolean isMore) {
        if (listData.isStatus()) {
            if (!isMore)
                classifys.clear();
            classifys.addAll(listData.getTngou());
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHomeLogic.onLoadRemoteData(false, 1);
                mRecyclerView.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                mHomeLogic.onLoadRemoteData(true, page);
                mRecyclerView.loadMoreComplete();
            }
        }, 2000);
    }
}
