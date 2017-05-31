package com.fangku.commonlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 基类Fragment（懒加载）
 * Create by: chenwei.li
 * Date: 2016-05-26
 * time: 09:15
 * Email: lichenwei.me@foxmail.com
 */
public abstract class BaseLazyFragment extends Fragment implements IBaseFragment {


    //当前Fragment视图对象
    protected View mView;

    /**
     * 是否加载布局
     */
    protected boolean isPrepare;

    /**
     * 是否显示布局
     */
    protected boolean isVisiable;

    /**
     * 是否是第一次加载
     */
    protected boolean isFirst;


    /**
     * Activity对象，避免getActivity()出现null
     */
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * 创建Fragment
         */
        createFragment(savedInstanceState);

        /**
         * 绑定视图
         */
        if (mView == null) {
            mView = inflater.inflate(bindLayout(), container, false);
            isPrepare = true;
            isFirst = true;
        }

        /**
         * 依赖注入
         */
        ButterKnife.bind(this, mView);

        /**
         * 初始化视图,默认值
         */
        initView();

        return mView;
    }

    /**
     * 判断当前Fragment是否已经显示
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisiable = true;
            onVisible();
        } else {
            isVisiable = false;
        }
    }

    protected void onVisible() {
        lazy();
    }

    /**
     * 要进行懒加载的数据
     */
    protected void lazy() {
        if (isPrepare && isVisiable && isFirst) {
            lazyLoadData();
            isFirst = false;
        }
    }

    /**
     * 需要进行懒加载的数据
     */
    protected abstract void lazyLoadData();


    /**
     * 只需要空实现，所有的懒加载数据都在lazyLoadData里面执行
     */
    @Override
    public void getData() {
    }
}
