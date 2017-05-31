package com.fangku.fyz.modular_me.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.util.MyBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 余额提现
 * Created by bowen.ye
 * Date: 2016/7/14
 * Time: 10:16
 */
public class Me_Balance_Rollout extends MyBaseActivity {

    Context mContext = Me_Balance_Rollout.this;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Balance_Rollout.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_balance_rollout;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("余额转出");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setText("限额说明");
    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.btn_back, R.id.tv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title_right:
                break;
        }
    }
}
