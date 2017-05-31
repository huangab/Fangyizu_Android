package com.fangku.fyz.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.widget.CircularAnim;
import com.fangku.fyz.Fragment_Main;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.welcome.ProductTourActivity;
import com.fangku.fyz.widget.Dialog_Show;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.dialog.widget.NormalListDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by   jie.wang
 * Date: 2016/8/2
 * Time: 15:49
 */
public class Login_Main extends MyBaseActivity implements PlatformActionListener {

    @Bind(R.id.tv_to_register)
    TextView mTvToRegister;
    @Bind(R.id.tv_to_forget)
    TextView mTvToForget;
    @Bind(R.id.bt_to_login)
    Button mBtToLogin;
    @Bind(R.id.iv_login_head_img)
    RoundedImageView mIvLoginHeadImg;
    @Bind(R.id.et_login_uesr)
    EditText mEtLoginUesr;
    @Bind(R.id.et_login_password)
    EditText mEtLoginPassword;
    @Bind(R.id.cb_login_check)
    CheckBox mCbCheck;
    @Bind(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @Bind(R.id.pb_login_main)
    ProgressBar mPbLoginMain;
    @Bind(R.id.ll_login_father)
    LinearLayout mLlLoginFather;
    @Bind(R.id.iv_login_qq)
    ImageView mIvLoginQq;
    @Bind(R.id.iv_login_weixin)
    ImageView mIvLoginWeixin;
    @Bind(R.id.iv_login_weibo)
    ImageView mIvLoginWeibo;


    private Handler mHandler = new Handler();

    private String userList[] = {"13777777777", "15705912735", "15960260231"};//存储历史用户的列表

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Login_Main.class);
        mContext.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.login_main;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        //是否首次登录  是则跳到引导界面
        if (MyApplication.isFirst) {
            startActivity(new Intent(mContext, ProductTourActivity.class));
            MyApplication.isFirst = false;
        }
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        MobclickAgent.openActivityDurationTrack(false);
        ShareSDK.initSDK(this);
        mEtLoginUesr.setText("");

    }


    @Override
    public void getData() {

    }


    @OnClick({R.id.tv_to_register, R.id.tv_to_forget, R.id.bt_to_login, R.id.to_mian, R.id.ll_login_check, R.id.iv_login_qq,
            R.id.iv_login_weixin, R.id.iv_login_weibo, R.id.ll_login_mian_xiala})
    public void onClick(View view) {
        String username = mEtLoginUesr.getText().toString();
        String pwd = mEtLoginPassword.getText().toString();
        switch (view.getId()) {
            case R.id.ll_login_mian_xiala:
                showDialog();

                break;
            case R.id.iv_login_qq:

                threeLogin("QQ");


                break;
            case R.id.iv_login_weibo:

                threeLogin("微博");

                break;
            case R.id.iv_login_weixin:

                threeLogin("微信");

                break;
            case R.id.ll_login_check://明文密码

                CommonUtil.PasswordTrue(mCbCheck, mEtLoginPassword);

                break;
            case R.id.tv_to_register://前往注册
                startActivity(new Intent(getApplicationContext(), Login_Register.class));
                break;

            case R.id.tv_to_forget://前往忘记密码

                Login_Forget.launch(Login_Main.this, "");
                break;

            case R.id.bt_to_login://登录操作
                mTvToForget.setEnabled(false);
                mTvToRegister.setEnabled(false);
                mIvLoginQq.setEnabled(false);
                mIvLoginWeibo.setEnabled(false);
                mIvLoginWeixin.setEnabled(false);


                CircularAnim.hide(mBtToLogin)
//                        .endRadius(mPbLoginMain.getHeight() / 2)
                        .go(() -> {
                            mPbLoginMain.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(() -> {

                                //一键登录
                                Login_Quick_http.Login(mContext, username, pwd, result -> {
                                    if ("登录成功".equals(result)) {
                                        mBtToLogin.getBackground().setAlpha(0);//0~255透明度值
                                        //登录成功之后做什么
                                        CircularAnim.fullActivity(Login_Main.this, mPbLoginMain)
                                                .go(() -> {
                                                    startActivity(new Intent(mContext, Fragment_Main.class));

                                                    finish();
                                                    MobclickAgent.onProfileSignIn(mEtLoginUesr.getText().toString().trim());//友盟统计
                                                    MyApplication.isLogin = true;
                                                    Static_Conmom.IsUpdate_Head = true;
                                                });
                                    } else {
                                        mBtToLogin.setVisibility(View.VISIBLE);
                                    }
                                    mTvToForget.setEnabled(true);
                                    mTvToRegister.setEnabled(true);
                                    mIvLoginQq.setEnabled(true);
                                    mIvLoginWeibo.setEnabled(true);
                                    mIvLoginWeixin.setEnabled(true);

                                });
                            }, 500);


                        });
                break;
            case R.id.to_mian:
                startActivity(new Intent(mContext, Fragment_Main.class));
                break;
        }

    }

    private void showDialog() {

        NormalListDialog listDialog = new NormalListDialog(mContext, userList);
        BaseAnimatorSet bas_in = new BounceEnter();

        listDialog.title("使用最近登陆过的帐号?")//

                .titleTextSize_SP(Static_Conmom.TITLE_TEXT_SIZE)//
                .titleBgColor(Color.parseColor(Static_Conmom.BACK_COLOR))//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(Static_Conmom.DIALOG_TEXT_SIZE)//
                .layoutAnimation(null)
                .cornerRadius(0)//
                .widthScale(0.8f)//
                .cornerRadius(2)
                .showAnim(bas_in)//
                .dismissAnim(null)//
                .show();

        listDialog.setOnOperItemClickL((parent, view, position, id) -> {
            listDialog.dismiss();
            mEtLoginUesr.setText(userList[position]);
            mEtLoginUesr.requestFocus();


        });

    }


    //第三方登录
    private void threeLogin(String type) {
        mLoadingDialog.show(mContext);
        switch (type) {
            case "QQ":
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.setPlatformActionListener(this);
                // 关闭SSO授权
                qq.SSOSetting(false);
                qq.showUser(null);
                Static_Conmom.ThreeLogin_Type = "QQ";

                break;
            case "微信":
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);

                wechat.SSOSetting(false);  //设置false表示使用SSO授权方式

                wechat.setPlatformActionListener(this); // 设置分享事件回调

                wechat.showUser(null);
                Static_Conmom.ThreeLogin_Type = "wechat";

                break;
            case "微博":
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);

                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式

                weibo.setPlatformActionListener(this); // 设置分享事件回调

                weibo.showUser(null);
                Static_Conmom.ThreeLogin_Type = "weibo";

                break;
        }

    }


    @Override //点击后退弹出确认框
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Dialog_Show.showTwoButton(mContext, "警告", "是否退出房易租?", "返回", "退出", result -> {
                if (result.equals("2")) {
                    ShareSDK.stopSDK(Login_Main.this);
                    System.exit(0);
                }
            });
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onResume() {
        super.onResume();
        mLoadingDialog.cancel();
    }


    //第三方回调开始
    @Override
    public void onComplete(final Platform platform, int i, HashMap<String, Object> hashMap) {

        Log.i("登录类型", Static_Conmom.ThreeLogin_Type);

        if (i == Platform.ACTION_USER_INFOR) {
            final PlatformDb platDB = platform.getDb();//获取数平台数据DB

            //通过DB获取各种数据
            Log.i("token", platDB.getToken());
            Log.i("usergender", platDB.getUserGender());
            Log.i("usericon", platDB.getUserIcon());
            Log.i("userid", platDB.getUserId());
            Log.i("username", platDB.getUserName());

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showLong(Login_Main.this, "userid:" + platDB.getUserId() + "username:" + platDB.getUserName() + "登录成功!");
                    platform.removeAccount();//清除第三方缓存信息
                }
            });
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        ToastUtil.showShort(Login_Main.this, "授权失败!");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        ToastUtil.showShort(getApplicationContext(), "取消授权!");
    }


    //第三方回调结束


}
