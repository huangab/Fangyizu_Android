package com.fangku.fyz.modular_me.setting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.utils.SPUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.cacheUtil;
import com.fangku.fyz.Fragment_Main;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.login.Login_Forget;
import com.fangku.fyz.login.Login_Main;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.widget.Dialog_Show;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.igexin.sdk.PushManager;
import com.kyleduo.switchbutton.SwitchButton;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 设置界面
 * Created by 67343 on 2016/7/14.
 */
public class Me_Setting extends MyBaseActivity {

    public static Me_Setting Me_Setting_this;//由于不能正在网络回调用使用activitymanage关闭 ,所以手动定义一个来finish


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;

    @Bind(R.id.ll_setting_message_get)
    LinearLayout mLlSettingMessageGet;
    @Bind(R.id.sb_text_state)
    SwitchButton mSbTextState;


    String cacheSize = "";

    File imageCacheFile;
    File PackageFile;
    File getPackagCache;
    @Bind(R.id.tv_cacheSize)
    TextView mTvCacheSize;

    private String[] stringItems = {"重置支付密码", "重置登录密码"};


    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Setting.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_setting;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("设置中心");
        if ((boolean) SPUtil.get(Me_Setting.this, "推送状态", true)) {

            mSbTextState.setChecked(true);
            //初始化个推
            PushManager.getInstance().initialize(this.getApplicationContext());

        } else {
            mSbTextState.setChecked(false);
            PushManager.getInstance().stopService(this.getApplicationContext());

        }

        try {
            cacheSize = cacheUtil.getTotalCacheSize(mContext);
            mTvCacheSize.setText(cacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.btn_back, R.id.sb_text_state, R.id.ll_setting_message_get, R.id.ll_setting_clean, R.id.ll_setting_tell_me, R.id.ll_setting_the_fangyizu, R.id.ll_setting_password_change, R.id.bt_quit_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.ll_setting_message_get: //推送
                switchButton_Click(true);
                break;
            case R.id.ll_setting_clean: //清除缓存

                String text = "缓存大小为: " + cacheSize + " ,是否清除?";
                Dialog_Show.showTwoButton(mContext, "温馨提示", text, "取消", "确定",
                        result -> {
                            if (result.equals("2")) {


                                cacheUtil.clearAllCache(mContext);
                                try {
                                    cacheSize = cacheUtil.getTotalCacheSize(mContext);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                mTvCacheSize.setText(cacheSize);
//
                            }
                        });


                break;
            case R.id.ll_setting_tell_me:  //意见反馈
                Me_Tell_Me.launch(Me_Setting.this);
                break;
            case R.id.ll_setting_the_fangyizu://关于房易租
                Me_Setting_Of_The_App.launch(Me_Setting.this);
                break;
            case R.id.ll_setting_password_change:// 密码修改
                NormalListDialogCustomAttr();
                break;
            case R.id.bt_quit_login://退出登录

                Dialog_Show.showTwoButton(mContext, "警告", "是否确定退出登录", "返回", "退出", result -> {
                    if (result.equals("2")) {
                        SPUtil.remove(mContext, "USER");
                        Login_Main.launch(mContext);
                        ActivityManager.getInstance().removeActivity(Me_Setting.class);
                        ActivityManager.getInstance().removeActivity(Fragment_Main.class);
                        ToastUtil.showShort(getApplicationContext(), "您已退出!");
                    }
                });
                break;
        }
    }


    /**
     * switchbutton的状态改变
     *
     * @param type
     */
    private void switchButton_Click(boolean type) {
        if (mSbTextState.isChecked()) {
            if (type) {
                mSbTextState.setChecked(false);
                SPUtil.put(Me_Setting.this, "推送状态", false);
                //停止个推SDK
                PushManager.getInstance().stopService(this.getApplicationContext());
            }
            Log.i("状态", (boolean) SPUtil.get(Me_Setting.this, "推送状态", true) + "");
        } else {

            if (type) {
                mSbTextState.setChecked(true);
                SPUtil.put(Me_Setting.this, "推送状态", true);
                // 重新初始化个推sdk
                PushManager.getInstance().initialize(this.getApplicationContext());
            }
            Log.i("状态", (boolean) SPUtil.get(Me_Setting.this, "推送状态", true) + "");

        }

    }


    //重置密码弹出框
    private void NormalListDialogCustomAttr() {
        final NormalListDialog dialog = new NormalListDialog(Me_Setting.this, stringItems);
        dialog.title("请选择")//
                .titleTextSize_SP(18)//
                .titleBgColor(Color.parseColor(Static_Conmom.BACK_COLOR))//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .layoutAnimation(null)
                .itemTextSize(14)//
                .cornerRadius(0)//
                .widthScale(0.8f)//
                .cornerRadius(5)
                .show(R.style.myDialogAnim);

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = stringItems[position];
                if (name.equals(stringItems[1])) {//重置登录密码
                    Login_Forget.launch(Me_Setting.this, UserDataUtil.getUserInfo().getData().getUsername());

                } else {//重置支付密码
                    ToastUtil.showShort(getApplicationContext(), name);
                }
                dialog.dismiss();
            }
        });
    }



}
