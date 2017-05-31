package com.fangku.fyz;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_hydropower.Fragment_Hydropower;
import com.fangku.fyz.modular_me.Fragment_Me;
import com.fangku.fyz.modular_rent.Fragment_Rent;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.widget.Dialog_Show;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;

/**
 * 承载fragment的主界面
 * Created by   jie.wang
 * Date: 2016/7/11
 * Time: 14:39
 */
public class Fragment_Main extends MyBaseActivity {

//    public static Fragment_Main Fragment_Main_this;//由于不能正在网络回调用使用activitymanage关闭 ,所以手动定义一个来finish


    public Fragment mFra_house_view; // 4个碎片
    public Fragment mFra_hydropower_view;
    public Fragment mFra_rent_view;
    public Fragment mFra_me_view;


    @Bind(R.id.frame_tab)
    FrameLayout mFrameTab;
    @Bind(R.id.iv_house_tab)
    ImageView mIvHouseTab;
    @Bind(R.id.tv_house_tab)
    TextView mTvHouseTab;
    @Bind(R.id.ll_house_tab)
    LinearLayout mLlHouseTab;
    @Bind(R.id.iv_hydropower_tab)
    ImageView mIvHydropowerTab;
    @Bind(R.id.tv_hydropower_tab)
    TextView mTvHydropowerTab;
    @Bind(R.id.ll_hydropower_tab)
    LinearLayout mLlHydropowerTab;
    @Bind(R.id.iv_rent_tab)
    ImageView mIvRentTab;
    @Bind(R.id.tv_rent_tab)
    TextView mTvRentTab;
    @Bind(R.id.ll_rent_tab)
    LinearLayout mLlRentTab;
    @Bind(R.id.iv_me_tab)
    ImageView mIvMeTab;
    @Bind(R.id.tv_me_tab)
    TextView mTvMeTab;
    @Bind(R.id.ll_me_tab)
    LinearLayout mLlMeTab;

    Handler mHandler;

    @Override
    public int bindLayout() {
        return R.layout.tab_main;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        setSelect(0);


    }


    @Override
    public void getData() {

    }

    @OnClick({R.id.ll_house_tab, R.id.ll_hydropower_tab, R.id.ll_rent_tab, R.id.ll_me_tab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_house_tab:
                resetImg();
                setSelect(0);
                break;
            case R.id.ll_hydropower_tab:
                resetImg();
                setSelect(1);
                break;
            case R.id.ll_rent_tab:
                resetImg();
                setSelect(2);
                break;
            case R.id.ll_me_tab:
                resetImg();
                setSelect(3);
                break;
        }
    }


    /**
     * 1.吧图片切换成亮的
     * 2.设置内容区域
     * 绑定FRAGMENT
     *
     * @param i
     */
    public void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();// 拿到FRAGMENT 管理器
        FragmentTransaction transaction = fm.beginTransaction();// 开启一个事务

        hideFragment(transaction);// 所有的先隐藏 然后显示出我们想要的那个就行了 传入事务参数


        switch (i) {
            case 0:// 判断mtab01是否为空，是则初始化，否则直接显示出来
                if (mFra_house_view == null) {
                    mFra_house_view = new Fragment_House();
                    transaction.add(R.id.frame_tab, mFra_house_view);
                }//两个参数，一个控件 ，一个布局，将控件传到布局里
                else {
                    transaction.show(mFra_house_view);

                }
                mIvHouseTab.setImageResource(R.mipmap.tab_house);
                mTvHouseTab.setTextColor(ContextCompat.getColor(mContext, R.color.activation));

                break;

            case 1:
                if (mFra_hydropower_view == null) {
                    mFra_hydropower_view = new Fragment_Hydropower();
                    transaction.add(R.id.frame_tab, mFra_hydropower_view);
                } else {
                    transaction.show(mFra_hydropower_view);
                }


                mIvHydropowerTab.setImageResource(R.mipmap.tab_caobiao);
                mTvHydropowerTab.setTextColor(ContextCompat.getColor(mContext, R.color.activation));

                break;
            case 2:
                if (mFra_rent_view == null) {
                    mFra_rent_view = new Fragment_Rent();
                    transaction.add(R.id.frame_tab, mFra_rent_view);
                } else {
                    transaction.show(mFra_rent_view);
                }


                mIvRentTab.setImageResource(R.mipmap.tab_shouzu);
                mTvRentTab.setTextColor(ContextCompat.getColor(mContext, R.color.activation));

                break;
            case 3:
                if (mFra_me_view == null) {
                    mFra_me_view = new Fragment_Me();
                    transaction.add(R.id.frame_tab, mFra_me_view);
                } else {
                    transaction.show(mFra_me_view);
                }


                mIvMeTab.setImageResource(R.mipmap.tab_me);
                mTvMeTab.setTextColor(ContextCompat.getColor(mContext, R.color.activation));
                break;

        }

        transaction.commit();

    }


    // TODO 隐藏事物 方法
    public void hideFragment(FragmentTransaction transaction) {

        if (mFra_house_view != null) { // 如果不为空，首先把他们4个都隐藏，然后 在setSelect中设置显示
            transaction.hide(mFra_house_view);
        }
        if (mFra_hydropower_view != null) {
            transaction.hide(mFra_hydropower_view);
        }
        if (mFra_rent_view != null) {
            transaction.hide(mFra_rent_view);
        }
        if (mFra_me_view != null) {
            transaction.hide(mFra_me_view);
        }

    }

    public void resetImg() {

        // TODO 切换图片成暗色 调用setImageResource 更换图片按钮控件中的图片
        mIvHouseTab.setImageResource(R.mipmap.tab_house_dark);
        mIvHydropowerTab.setImageResource(R.mipmap.tab_caobiao_dark);
        mIvRentTab.setImageResource(R.mipmap.tab_shouzu_dark);
        mIvMeTab.setImageResource(R.mipmap.tab_me_dark);


        mTvHouseTab.setTextColor(ContextCompat.getColor(Fragment_Main.this, R.color.no_activation));
        mTvHydropowerTab.setTextColor(ContextCompat.getColor(Fragment_Main.this, R.color.no_activation));
        mTvRentTab.setTextColor(ContextCompat.getColor(Fragment_Main.this, R.color.no_activation));
        mTvMeTab.setTextColor(ContextCompat.getColor(Fragment_Main.this, R.color.no_activation));


    }


    @Override // 点击后退按钮 提示是否退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Dialog_Show.showTwoButton(Fragment_Main.this, "温馨提示", "是否退出房易租?", "返回", "退出", result -> {
                if (result.equals("2")) {
                    ShareSDK.stopSDK(Fragment_Main.this);
                    System.exit(0);
                }
            });


//                        //动态注册广播
//                        IntentFilter filter = new IntentFilter();
//                        filter.addAction(Static_Conmom.ACTION_QUIT);
//                        PushReceiver pushReceiver = new PushReceiver();
//                        registerReceiver(pushReceiver, filter);


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);       //统计时长
        System.gc();
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
