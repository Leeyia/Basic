package com.racofix.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author: 蜡笔小新
 * @date: 2016-07-08 16:51
 * @GitHub: https://github.com/meikoz
 */
public class UiScrollView extends ScrollView {
    private Callback mCallback;

    public UiScrollView(Context context) {
        super(context);
    }

    public UiScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UiScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallback != null) {
            mCallback.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }
}
