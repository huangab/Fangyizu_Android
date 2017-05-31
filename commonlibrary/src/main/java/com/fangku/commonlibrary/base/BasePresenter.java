package com.fangku.commonlibrary.base;

/**
 * 基类BasePresenter
 * Create by: chenwei.li
 * Date: 2016-05-31
 * time: 11:14
 * Email: lichenwei.me@foxmail.com
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }
}
