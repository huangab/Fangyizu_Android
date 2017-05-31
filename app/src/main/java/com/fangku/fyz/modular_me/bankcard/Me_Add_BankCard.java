package com.fangku.fyz.modular_me.bankcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.ValidateUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.util.BankCardUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加银行卡
 * Created by   jie.wang
 * Date: 2016/8/12
 * Time: 14:56
 */
public class Me_Add_BankCard extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.sb_backcard_state)
    SwitchButton mSbBackcardState;
    @Bind(R.id.tv_bank_type)
    TextView mTvBankType;
    @Bind(R.id.et_bank_cardnum)
    EditText mEtBankCardnum;
    @Bind(R.id.et_bank_card_person)
    EditText mEtBankCardPerson;
    @Bind(R.id.et_bank_card_phone)
    EditText mEtBankCardPhone;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Add_BankCard.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_bankcard_add;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("添加银行卡");


        mEtBankCardnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 19 || s.length() == 16) {
                    String type = BankCardUtil.getNameOfBank(mEtBankCardnum.getText().toString());
                    mTvBankType.setText(type);
                } else {
                    mTvBankType.setText("输入卡号之后自动识别");
                }
            }
        });

    }

    @Override
    public void getData() {


    }


    @OnClick({R.id.btn_back, R.id.bt_add_bankcard_ok, R.id.ll_aotu_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_add_bankcard_ok:
                add_bankcard();
                break;
            case R.id.ll_aotu_service:
                switchButton_Click(true);

                break;
        }
    }

    private void add_bankcard() {
        if (mTvBankType.getText().toString().equals("输入卡号之后自动识别") && mEtBankCardnum.getText().toString().length() < 19) {
            ToastUtil.showShort(mContext, "请输入正确的卡号!");
            return;
        }
        if (mEtBankCardPerson.getText().toString().trim().length() < 2) {
            ToastUtil.showShort(mContext, "姓名格式不正确!");
            return;
        }
        if (!ValidateUtil.isMobilePhone(mEtBankCardPhone.getText().toString())) {
            ToastUtil.showShort(mContext, "不是标准的手机号!");
            return;
        }


        mLoadingDialog.show(mContext);
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mEtBankCardPhone.getText().toString());
        map.put("CardNo", mEtBankCardnum.getText().toString());
        map.put("name", mEtBankCardPerson.getText().toString());
        map.put("cardType", mTvBankType.getText().toString());
        map.put("validNo", "暂无");

        new PostUtil().Post_Bean(Url_final.ADD_BANKCARD, map).execute(new Bean_Callback<BaseResponse>(mContext) {
            @Override
            protected void onSuccess_Code200(BaseResponse response, String message) {
                finish();
            }

            @Override
            protected void onOver() {
                mLoadingDialog.cancel();
            }
        });

    }

    /**
     * switchbutton的状态改变
     *
     * @param type
     */
    private void switchButton_Click(boolean type) {
        if (mSbBackcardState.isChecked()) {
            if (type) {
                mSbBackcardState.setChecked(false);
            }
            Log.i("状态", mSbBackcardState.isChecked() + "");
        } else {

            if (type) {
                mSbBackcardState.setChecked(true);
            }
            Log.i("状态", mSbBackcardState.isChecked() + "");
        }

    }


}
