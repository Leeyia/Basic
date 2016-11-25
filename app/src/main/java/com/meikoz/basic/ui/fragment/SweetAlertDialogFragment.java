package com.meikoz.basic.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.meikoz.basic.R;
import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.ui.SweetAlertDialog;
import butterknife.OnClick;

/**
 * @User: 蜡笔小新
 * @date: 16-11-25
 * @GitHub: https://github.com/meikoz
 */

public class SweetAlertDialogFragment extends BaseFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sweetalert_dialog;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
    }

    @OnClick({R.id.btn_normal_cancle, R.id.btn_normal_confirm, R.id.btn_normal_custom})
    void onSweetAlertDialogClick(View v) {
        SweetAlertDialog.Builder builder = new SweetAlertDialog.Builder(getActivity());
        builder.setTitle("标题");
        builder.setMessage("描述详细内容?");
        switch (v.getId()) {
            case R.id.btn_normal_cancle:
                builder.setCancelable(true)
                        .setPositiveButton("确认", new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;

            case R.id.btn_normal_confirm:
                builder.setCancelable(false)
                        .setPositiveButton("确认", new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;

            case R.id.btn_normal_custom:
                builder.setCancelable(false)
                        .setNegativeButton("左边", new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                Toast.makeText(getActivity(), "左边", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确认", new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
        builder.show();
    }
}
