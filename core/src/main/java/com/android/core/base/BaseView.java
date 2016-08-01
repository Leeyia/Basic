package com.android.core.base;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-21 18:09
 * @GitHub: https://github.com/meikoz
 */
public interface BaseView {
    void showMessage(String msg);

    void showProgress(String msg);

    void showProgress(String msg, int progress);

    void hideProgress();

    void showErrorMessage(String msg, String content);
}
