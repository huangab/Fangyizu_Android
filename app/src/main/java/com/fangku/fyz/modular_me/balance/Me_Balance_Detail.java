package com.fangku.fyz.modular_me.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_Me_Balance_Detail;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 余额明细
 * Created by bowen.ye
 * Date: 2016/7/14
 * Time: 15:06
 */
public class Me_Balance_Detail extends MyBaseActivity {

    Context mContext = Me_Balance_Detail.this;


    List<Bean_Me_Balance_Detail> mList = new ArrayList<>();
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.rl_title_top)
    RelativeLayout mRlTitleTop;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Balance_Detail.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_balance_detail;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("余额明细");


        Bean_Me_Balance_Detail bean_me_balance_detail = new Bean_Me_Balance_Detail();
        bean_me_balance_detail.setType("在线支付");
        bean_me_balance_detail.setBalance("余额：200");
        bean_me_balance_detail.setTime("2016-07-14");
        bean_me_balance_detail.setPay("+200");
        mList.add(bean_me_balance_detail);


    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.tv_title, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:

                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
