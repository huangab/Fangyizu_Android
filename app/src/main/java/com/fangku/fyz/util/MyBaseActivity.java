package com.fangku.fyz.util;/**
 * Created by 67343 on 2016/7/29.
 */

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.base.IBaseActivity;
import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.widget.dialog.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public abstract class MyBaseActivity extends AppCompatActivity implements IBaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    //当前Activity视图对象
    protected View mView;

    public LoadingDialog mLoadingDialog = new LoadingDialog();

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//
        /**
         * 打印当前Activity名称
         */
        mContext = this;

        /**
         * 绑定视图
         */
        if (mView == null) {
            mView = LayoutInflater.from(this).inflate(bindLayout(), null);
        }


        setContentView(mView);
        /**
         * 依赖注入
         */
        ButterKnife.bind(this);

        /**
         *创建Activity
         */
        createActivity(savedInstanceState);

        /**
         * 初始化视图(设置默认值)
         */
        initView();
        /**
         * 获取对应数据(网络，数据库)
         */
        getData();

        /**
         * 加入ActivityManager管理队列
         */
        ActivityManager.getInstance().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 解绑ButterKnife
         */
        ButterKnife.unbind(this);
        /**
         * 销毁Activity
         */
        ActivityManager.getInstance().removeActivity(this);
    }

    public void onResume() {
        super.onResume();
        LogUtil.i("当前运行的Activity是:", TAG + ".class");
        MobclickAgent.onPageStart(TAG); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长

    }

    public void onPause() {
        super.onPause();

        MobclickAgent.onPageEnd(TAG); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }
}
