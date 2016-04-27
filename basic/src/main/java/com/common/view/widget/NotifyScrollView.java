package com.common.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NotifyScrollView extends ScrollView {

    private Callback mCallback;

    public NotifyScrollView(Context context) {
        super(context);
    }

    public NotifyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotifyScrollView(Context context, AttributeSet attrs, int defStyle) {
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
