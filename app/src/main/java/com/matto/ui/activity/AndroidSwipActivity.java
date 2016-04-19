package com.matto.ui.activity;

import android.os.Bundle;

import com.common.view.base.SwipeBackActivity;
import com.common.view.base.SwipeBackLayout;
import com.matto.R;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class AndroidSwipActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }
}
