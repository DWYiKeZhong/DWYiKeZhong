package com.example.yikezhong.ui.base;

/**
 * 所有Presenter的积累，实现了BaseContract.BasePresenter接口
 *
 * @param <T>
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;

    @Override
    public void attchView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
