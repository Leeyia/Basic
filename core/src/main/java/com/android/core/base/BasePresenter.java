package com.android.core.base;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-21 17:41
 * @GitHub: https://github.com/meikoz
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T mView;

    @Override
    public void attachView(T mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public boolean isViewBind() {
        return mView != null;
    }


    public T getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!isViewBind()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }
}
