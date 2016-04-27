package com.matto.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.common.model.control.LogicProxy;
import com.common.utils.StatusBarUtil;
import com.common.view.base.BaseFragment;

import com.common.view.base.BaseRecyclerAdapter;
import com.common.view.base.BaseRecyclerViewHolder;
import com.common.view.callback.OnItemClickAdapter;
import com.common.view.widget.AutoLoadMoreRecyclerView;
import com.matto.R;
import com.matto.model.bean.Classify;
import com.matto.presenter.CompeteLogic;
import com.matto.ui.activity.SwipBackActivity;
import com.matto.ui.view.CompeteView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author meikoz on 2016/3/30.
 * email  meikoz@126.com
 */
public class CompeteFragment extends BaseFragment implements CompeteView {
    private String URL_Base = "http://tnfs.tngou.net/image";
    @Bind(R.id.recly_view)
    AutoLoadMoreRecyclerView mReclyView;

    StaggeredGridLayoutManager manager;
    List<Classify.TngouEntity> list = new ArrayList<>();
    private CompeteLogic competeLogic;
    private BaseRecyclerAdapter<Classify.TngouEntity> adapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_compete;
    }

    @Override
    protected void onInitView() {
        StatusBarUtil.setColor(getActivity(), R.color.white);
        competeLogic = LogicProxy.getInstance().bind(CompeteLogic.class, this);
        competeLogic.onRefreshData();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new BaseRecyclerAdapter<Classify.TngouEntity>(getActivity(), list) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_compete_classitfy;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, Classify.TngouEntity item) {
                Uri uri = Uri.parse(URL_Base + item.getImg());
                Glide.with(mContext).load(uri).asBitmap().animate(R.anim.image_load)
                        .into(holder.getImageView(R.id.sv_classitfy_img));

                holder.setText(R.id.sv_classitfy_des, item.getTitle());
            }
        };

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        mReclyView.setAutoLayoutManager(linearLayoutManager).setAutoHasFixedSize(true)
//                .addAutoItemDecoration(
//                        new BaseSpacesItemDecoration(DisplayUtil.dp2px(getActivity(), 0)))
                .setAutoItemAnimator(new DefaultItemAnimator()).setAutoAdapter(adapter);

        mReclyView.setOnLoadMoreListener(new AutoLoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                Log.e("loadmore", "加载更多");
                adapter.showFooter();
                competeLogic.onLoadMoreData();
//                mReclyView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickAdapter() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(mReclyView, position + "", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), SwipBackActivity.class));
            }
        });
    }

    @Override
    public void onSuccess(List<Classify.TngouEntity> list) {
        adapter.addMoreData(list);
//        adapter.hideFooter();
    }
}
