package com.fangku.commonlibrary.base;

/**
 * 基类Presenter接口
 * Create by: chenwei.li
 * Date: 2016-05-31
 * time: 14:25
 * Email: lichenwei.me@foxmail.com
 */
public interface IBasePresenter<V extends IBaseView> {

    /**
     * V层注入引用
     * @param mvpView
     */
    void attachView(V mvpView);

    /**
     * 销毁V层引用
     */
    void detachView();


}
