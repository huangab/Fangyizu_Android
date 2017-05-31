package com.fangku.fyz.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by   jie.wang
 * Date: 2016/8/8
 * Time: 18:55
 */
public class Login_Register_2 extends MyBaseActivity {

    public static final String PHONE = "1";
    public static final String PWD = "2";
    public static final String USERCODE = "3";
    public static final String HEAD = "4";
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_phone_number)
    TextView mTvPhoneNumber;
    @Bind(R.id.et_reg_messagecade)
    EditText mEtRegMessagecade;
    @Bind(R.id.tv_reg_getcode)
    TextView mTvRegGetcode;


    private String phone;
    private String pwd;
    private String userCade;
    private File head;

    public static void lanuch(Context context, String phone, String pwd, String userCade, String head) {
        Intent intent = new Intent(context, Login_Register_2.class);
        intent.putExtra(PHONE, phone);
        intent.putExtra(PWD, pwd);
        intent.putExtra(USERCODE, userCade);
        intent.putExtra(HEAD, head);
        context.startActivity(intent);

    }


    @Override
    public int bindLayout() {
        return R.layout.login_register2;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("填写验证码");

        Intent intent = getIntent();
        phone = intent.getStringExtra(PHONE);
        pwd = intent.getStringExtra(PWD);
        userCade = intent.getStringExtra(USERCODE);
        head = new File(intent.getStringExtra(HEAD));
        String new_phone = CommonUtil.phone_set_null(phone);
        mTvPhoneNumber.setText("+86 " + new_phone);

        MyApplication.startTime(mTvRegGetcode);


    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.btn_back, R.id.tv_reg_getcode, R.id.bt_to_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_reg_getcode:

                Login_Quick_http.message(Login_Register_2.this, phone, mLoadingDialog, "/1", new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        MyApplication.startTime(mTvRegGetcode);
                    }
                });

                break;
            case R.id.bt_to_register:
                Login_Quick_http.register(Login_Register_2.this, mLoadingDialog, phone,
                        mEtRegMessagecade.getText().toString(), new Only_CallBack() {
                            @Override
                            public void isSuccess(String result) {
                                Login_Register.register_this.finish();
                                finish();


                            }
                        });

                break;
        }
    }


}
