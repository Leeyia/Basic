package com.zhoujinlong.ui.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zhoujinlong.R;

/**
 * @ClassName: XListView.java
 * @author: 蜡笔小新
 * @date: 2016-04-28 17:53
 */
public class AutoLoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    int mFirstVisibleItem; //当前第一个可见Item的位置
    int mTotalItemCount;
    int mLastVisibleItem;  //最后一个可见Item的位置
    boolean isMarkItem; // 标记第一个出现的Item

    int mScrollState; // 滚动状态
    int state; //当前状态

    final int NONE = 0; // 正常状态
    final int PULL = 1; // 下拉刷新状态
    final int RELESE = 2; // 松开释放状态

    boolean isLoading;  //正在加载
    private View mFooterView;
    private ILoadMoreCallback mCallback;
    private int startY;

    public AutoLoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public AutoLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AutoLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mFooterView = inflater.inflate(R.layout.view_footer_layout, null);
        mFooterView.setVisibility(GONE);

        // 设置滚动监听
        setOnScrollListener(this);
        // 去掉底部分割线
        setFooterDividersEnabled(false);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mTotalItemCount = totalItemCount;
        mLastVisibleItem = firstVisibleItem + visibleItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mScrollState = scrollState;
        if (mTotalItemCount == mLastVisibleItem && mScrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                mFooterView.setVisibility(VISIBLE);
                mCallback.onLoadMore();
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mFirstVisibleItem == 0) {
                    isMarkItem = true;
                    startY = (int) ev.getY();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(ev);
    }


    /*
     * 判断移动过程中的操作
     */
    private void onMove(MotionEvent e) {

    }


    public void setOnLoadMoreListener(ILoadMoreCallback callback) {
        mCallback = callback;
    }

    public interface ILoadMoreCallback {
        void onRefresh();

        void onLoadMore();
    }
}
