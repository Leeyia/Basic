package com.racofix.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.core.ui.BaseFragment;
import com.android.core.control.XRecyclerViewHelper;
import com.android.core.widget.CustomViewpager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.racofix.R;
import com.racofix.presenter.MainLogic;
import com.racofix.presenter.core.BaseListView;
import com.racofix.view.adapter.CustomViewPageAdapter;
import com.racofix.view.adapter.HomeRecyclerAdapter;
import com.android.core.adapter.RecyclerAdapter;
import com.racofix.model.pojo.Classify;
import com.racofix.presenter.IMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 10:51
 * @GitHub: https://github.com/meikoz
 */
public class HomeFragment extends BaseFragment implements BaseListView<Classify>, XRecyclerView.LoadingListener {

    @Bind(R.id.recly_view)
    XRecyclerView mRecyclerView;

    private List<Classify.TngouEntity> classifys = new ArrayList<>();
    private CustomViewpager mViewpage;
    private RecyclerAdapter recyclerAdapter;
    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_compete;
    }

    @Override
    protected void onInitView() {
        mPresenter = getLogicImpl(IMain.class, this);
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

        XRecyclerViewHelper.init().setLinearLayout(getActivity(), mRecyclerView);

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
        showLoadingView();
        onRefresh();
    }

    @Override
    public void onLoadComplete(boolean isMore) {
        //加载完成需要做的操作
        hideLoadingView();
        if (isMore)
            mRecyclerView.loadMoreComplete();
        else
            mRecyclerView.refreshComplete();
    }

    /**
     * 网络加载成功后显示数据
     */
    @Override
    public void onResponseLData(Classify listData, boolean isMore) {
        if (listData.isStatus()) {
            if (!isMore)
                classifys.clear();
            classifys.addAll(listData.getTngou());
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        ((MainLogic) mPresenter).onLoadRemoteData(false, 1);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((MainLogic) mPresenter).onLoadRemoteData(true, page);
    }

}
