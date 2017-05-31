package com.fangku.fyz;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.fangku.commonlibrary.utils.SPUtil;
import com.fangku.fyz.login.Login_Main;
import com.fangku.fyz.util.MyBaseActivity;
import com.igexin.sdk.PushManager;

/**
 * 登录页面
 */
public class MainActivity extends MyBaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {



        if (SPUtil.contains(mContext, "First")) {
            MyApplication.isFirst = false;

        } else {
            MyApplication.isFirst = true;
            SPUtil.put(mContext, "First", "非首次登录");



        }


        new Handler().postDelayed(() -> {


            Login_Main.launch(mContext);
            //关于个推
            start_Getui();

            finish();


        }, 2000);


    }

    private void start_Getui() {


        if (!SPUtil.contains(MainActivity.this, "First")) {//如果首次开启APP不存在 默认为开启
            SPUtil.put(MainActivity.this, "推送状态", true);
            //初始化个推
            PushManager.getInstance().initialize(this.getApplicationContext());
        } else {

            if ((boolean) SPUtil.get(MainActivity.this, "推送状态", true)) {
                //初始化个推
                PushManager.getInstance().initialize(this.getApplicationContext());
                SPUtil.put(MainActivity.this, "推送状态", true);
            } else {
                PushManager.getInstance().stopService(this.getApplicationContext());
                SPUtil.put(MainActivity.this, "推送状态", false);
            }
        }
    }

    @Override
    public void getData() {

    }

    @Override // 初始化页面下禁止后退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
