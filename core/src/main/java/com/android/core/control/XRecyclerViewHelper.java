package com.android.core.control;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.core.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-01 17:42
 * @GitHub: https://github.com/meikoz
 */
public class XRecyclerViewHelper {

    static XRecyclerViewHelper ourInstance;

    public XRecyclerViewHelper() {

    }

    public static XRecyclerViewHelper init() {
        if (ourInstance == null)
            ourInstance = new XRecyclerViewHelper();
        return ourInstance;
    }


    public void setLinearLayout(Context context, XRecyclerView mRecy) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecy.setLayoutManager(layoutManager);
        //设置分隔线
//        mRecy.addItemDecoration(new SpacesItemDecoration(1));
        mRecy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecy.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecy.setArrowImageView(R.drawable.abc_icon_down_arrow);
    }

}
