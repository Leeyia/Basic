package com.meikoz.core.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.meikoz.core.R;

public class SweetAlertDialog {

    private Context context;
    private int titleIds;
    private String title;
    private String message;
    private boolean hasCancle;
    private TextView mContentTitle;
    private TextView mContentMessage;
    private Dialog mDialog;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;
    private OnDialogClickListener mNegativeButtonListener;
    private OnDialogClickListener mPositiveButtonListener;
    private TextView mLeftTxt;
    private TextView mRightTxt;
    private View mCenterLine;


    public SweetAlertDialog(Builder builder) {
        this.context = builder.mContext;
        this.titleIds = builder.mTitleResId;
        this.title = builder.mTitle;
        this.message = builder.mMessage;
        this.hasCancle = builder.mHasCancleable;
        this.mNegativeButtonText = builder.mNegativeButtonText;
        this.mPositiveButtonText = builder.mPositiveButtonText;
        this.mNegativeButtonListener = builder.mNegativeButtonListener;
        this.mPositiveButtonListener = builder.mPositiveButtonListener;
        this.initView();
    }

    /**
     * 初始化布局文件
     */
    private void initView() {
        View rootView = LayoutInflater.from(context).inflate(R.layout.sweet_alert_dialog_view, null);
        mContentTitle = (TextView) rootView.findViewById(R.id.tv_dialog_title);
        mContentMessage = (TextView) rootView.findViewById(R.id.tv_dialog_message);
        mLeftTxt = (TextView) rootView.findViewById(R.id.dialog_left_txt);
        mRightTxt = (TextView) rootView.findViewById(R.id.dialog_right_txt);
        mCenterLine = rootView.findViewById(R.id.dialog_line);
        // 定义Dialog布局和参数
        mDialog = new Dialog(context, R.style.Sweet_Alert_Dialog);
        mDialog.setContentView(rootView);
        mDialog.setCanceledOnTouchOutside(false);
        updateDialogUI();
        mDialog.show();
    }

    private void updateDialogUI() {
        // title resId
        if (titleIds != 0) {
            mContentTitle.setVisibility(View.VISIBLE);
            mContentTitle.setText(titleIds);
        }
        // title
        if (hasNull(title)) {
            mContentTitle.setVisibility(View.VISIBLE);
            mContentTitle.setText(title);
        }

        // message
        if (hasNull(message)) {
            mContentMessage.setText(message);
        }

        // 默认显示取消按钮 自定义字体
        if (hasNull(mNegativeButtonText) || hasCancle) {
            mLeftTxt.setVisibility(View.VISIBLE);
            mLeftTxt.setText(hasCancle ? "取消" : mNegativeButtonText);
            mLeftTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDialog != null)
                        mDialog.dismiss();
                    if (!hasCancle)
                        mNegativeButtonListener.onClick(mDialog, 0);
                }
            });
        }

        //左侧文字为空,
        if (!hasNull(mNegativeButtonText) && !hasCancle && hasNull(mPositiveButtonText)) {
            mLeftTxt.setVisibility(View.GONE);
            mCenterLine.setVisibility(View.GONE);
        }

        if (hasNull(mPositiveButtonText)) {
            mRightTxt.setVisibility(View.VISIBLE);
            mRightTxt.setText(mPositiveButtonText);
            mRightTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDialog != null)
                        mDialog.dismiss();
                    mPositiveButtonListener.onClick(mDialog, 1);
                }
            });
        }
    }


    public boolean hasNull(CharSequence msg) {
        return !TextUtils.isEmpty(msg);
    }

    public static class Builder {
        private Context mContext;
        private int mTitleResId;
        private String mTitle;
        private String mMessage;
        private boolean mHasCancleable = true;
        private CharSequence mNegativeButtonText;
        private CharSequence mPositiveButtonText;
        private OnDialogClickListener mNegativeButtonListener;
        private OnDialogClickListener mPositiveButtonListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.mTitleResId = titleId;
            return this;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setCancelable(boolean hasCancleable) {
            this.mHasCancleable = hasCancleable;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final OnDialogClickListener listener) {
            this.mNegativeButtonText = text;
            mNegativeButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final OnDialogClickListener listener) {
            this.mPositiveButtonText = text;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public SweetAlertDialog show() {
            return new SweetAlertDialog(this);
        }
    }

    public interface OnDialogClickListener {
        void onClick(Dialog dialog, int which);
    }
}


