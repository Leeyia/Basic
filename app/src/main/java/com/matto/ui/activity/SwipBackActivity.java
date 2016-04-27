package com.matto.ui.activity;

import android.os.Bundle;

import com.common.view.base.BaseSwipeBackActivity;
import com.common.view.widget.SwipeBackLayout;
import com.matto.R;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class SwipBackActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }
}
