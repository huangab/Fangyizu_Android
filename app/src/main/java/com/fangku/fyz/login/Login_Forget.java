package com.fangku.fyz.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.utils.SPUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.ValidateUtil;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 忘记密码——手机号验证
 * Created by   jie.wang
 * Date: 2016/7/11
 * Time: 15:32
 */
public class Login_Forget extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_forget_phonenumber)
    EditText mEtForgetPhonenumber;
    @Bind(R.id.et_forget_code)
    EditText mEtForgetCode;
    @Bind(R.id.tv_forget_getcode)
    TextView mTvForgetGetcode;
    @Bind(R.id.et_forget_pwd)
    EditText mEtForgetPwd;
    @Bind(R.id.cb_forget_check)
    CheckBox mCbForgetCheck;
    @Bind(R.id.et_forget_realname)
    EditText mEtForgetRealname;

    Context context = Login_Forget.this;

    private String getphone = "";


    public static void launch(Context mContext, String phone) {
        Intent mIntent = new Intent(mContext, Login_Forget.class);
        mIntent.putExtra("phone", phone);

        mContext.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.login_forget;
    }


    @Override
    public void createActivity(Bundle savedInstanceState) {

    }


    @Override
    public void initView() {


        Intent intent = getIntent();
        getphone = intent.getStringExtra("phone");
        if (getphone.equals("")) {
            mTvTitle.setText("找回密码");
        } else {
            mTvTitle.setText("重置登录密码");
            mEtForgetPhonenumber.setText(getphone);
            mEtForgetPhonenumber.setEnabled(false);
        }

        //验证码重置时间设置
        if (MyApplication.time_num != Static_Conmom.TIME_SIZE * 1000) {
            MyApplication.startTime(mTvForgetGetcode);
        }
    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.btn_back, R.id.tv_forget_getcode, R.id.ll_register_check, R.id.btn_forget_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_forget_getcode:  //获取验证码

                if (!ValidateUtil.isMobilePhone(mEtForgetPhonenumber.getText().toString())) {
                    ToastUtil.showShort(context, "请输入正确的手机号");

                    return;
                }

                Login_Quick_http.message(Login_Forget.this, mEtForgetPhonenumber.getText().toString(), mLoadingDialog, "/2", new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        MyApplication.startTime(mTvForgetGetcode);
                    }
                });
                break;
            case R.id.ll_register_check://明文密码

                CommonUtil.PasswordTrue(mCbForgetCheck, mEtForgetPwd);

                break;
            case R.id.btn_forget_next://找回密码

                if (!ValidateUtil.isMobilePhone(mEtForgetPhonenumber.getText().toString())) {
                    ToastUtil.showShort(context, "请输入正确的手机号");

                    return;
                }

                if (mEtForgetCode.getText().toString().length() < 6) {
                    ToastUtil.showShort(context, "验证码为6位数字!");
                    return;
                }

                if (TextUtils.isEmpty(mEtForgetPwd.getText().toString())) {
                    ToastUtil.showShort(context, "密码不能为空!");

                    return;
                }
                if (!CommonUtil.isHardPassword(mEtForgetPwd.getText().toString())) {
                    ToastUtil.showShort(context, "密码长度为8-20,必须带有英文字母!");

                    return;
                }

                //执行忘记密码网络访问
                Login_Quick_http.forget(Login_Forget.this, mLoadingDialog, mEtForgetPhonenumber.getText().toString(),
                        mEtForgetCode.getText().toString(), mEtForgetPwd.getText().toString(), "", new Only_CallBack() {
                            @Override
                            public void isSuccess(String result) {
                                if (!getphone.equals("")) {//如果是从重置密码进来的,值为""
                                    SPUtil.remove(mContext, "USER");
                                    ToastUtil.showShort(getApplicationContext(), "重置成功,请重新登录!");
                                    startActivity(new Intent(Login_Forget.this, Login_Main.class));
                                    ActivityManager.getInstance().removeAllActivity();


                                } else {//如果是从登录进来的
                                    finish();
                                }
                            }
                        });

                break;
        }
    }
}
