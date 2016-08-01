package com.racofix.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.core.control.ScreenUtil;
import com.racofix.R;

/**
 * author miekoz on 2016/3/15.
 * email  meikoz@126.com
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {

    LinearLayout mlvTitleRight;
    TextView mTvTitle;

    private boolean mIsBackFinishEnable;
    private OnClickTitleBarBackCallback mCallBack;
    private TextView mTvRightText;

    public TitleBar(Context context) {
        super(context);
        onInit();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInit();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.titlebarview);
        if (array.getResourceId(R.styleable.titlebarview_title_text, 0) != 0) {
            setTitle(array.getResourceId(R.styleable.titlebarview_title_text, 0));
        } else if (!TextUtils.isEmpty(array.getString(R.styleable.titlebarview_title_text))) {
            setTitle(array.getString(R.styleable.titlebarview_title_text));
        }
        setBackFinishEnable(array.getBoolean(R.styleable.titlebarview_backfinish_enable, true));
    }

    private void onInit() {
        View rootView = inflate(getContext(), R.layout.layout_nav_toolbar, this);
        mlvTitleRight = (LinearLayout) rootView.findViewById(R.id.lv_title_right);
        mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        int padding = ScreenUtil.dp2px(getContext(), 15);
        setPadding(padding, 0, padding, 0);
        setBackgroundResource(R.color.white);
        rootView.findViewById(R.id.lv_title_left).setOnClickListener(this);
    }

    /**
     * 设置点击返回按钮的回调事件
     *
     * @param callback
     */
    public void setOnClickBackCallback(OnClickTitleBarBackCallback callback) {
        this.mCallBack = callback;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.mTvTitle.setText(title);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        this.mTvTitle.setText(resId);
    }

    /**
     * 设置点击返回按钮后是否finish当前的Activity
     * 注意：默认是true
     *
     * @param enable
     */
    public void setBackFinishEnable(boolean enable) {
        this.mIsBackFinishEnable = enable;
    }

    /**
     * 向TitleBar右侧添加自定义View
     *
     * @param view
     */
    public void addRightView(View view) {
        mlvTitleRight.addView(view);
    }

    public void addRightView(View view, LinearLayout.LayoutParams params) {
        view.setLayoutParams(params);
        mlvTitleRight.addView(view);
    }

    private TextView getRightTextView() {
        if (mTvRightText == null) {
            mTvRightText = new TextView(getContext());
            mTvRightText.setTextSize(22);
            mTvRightText.setTextColor(Color.BLACK);
            mlvTitleRight.addView(mTvRightText);
        }
        return mTvRightText;
    }

    @Override
    public void onClick(View v) {
        if (mCallBack != null) {
            mCallBack.onClickBack(v);
        }

        if (mIsBackFinishEnable && getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

    public interface OnClickTitleBarBackCallback {
        /**
         * 点击返回View的回调事件
         *
         * @param view
         */
        void onClickBack(View view);
    }

}
