package com.fangku.fyz.util;/**
 * Created by 67343 on 2016/7/29.
 */

import android.os.Bundle;

import com.fangku.commonlibrary.base.BaseFragment;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class MyBaseFragment extends BaseFragment {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void createFragment(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG); //统计页面，"MainScreen"为页面名称，可自定义

    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }
}
