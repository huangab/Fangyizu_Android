package com.fangku.fyz.modular_me.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_me.bean.Bean_Me_Balance;
import com.fangku.fyz.util.MyBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的余额
 * Created by bowen.ye
 * Date: 2016/7/14
 * Time: 9:34
 */
public class Me_Balance extends MyBaseActivity {

    Context mContext = Me_Balance.this;

    @Bind(R.id.tv_me_balance)
    TextView mTvMeBalance;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.imageView)
    ImageView mImageView;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Balance.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_balance;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("我的余额");
        mImageView.setVisibility(View.GONE);

    }

    @Override
    public void getData() {

        mLoadingDialog.show(mContext);

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.GET_BALANCE).execute(new Bean_Callback<Bean_Me_Balance>(mContext) {
            @Override
            protected void onSuccess_Code200(Bean_Me_Balance response, String message) {
                mTvMeBalance.setText(response.getData().getAccountBalance());
            }

            @Override
            protected void onOver() {
                mLoadingDialog.cancel();
            }
        });
    }

    @OnClick({R.id.btn_back, R.id.tv_me_balance_query, R.id.tv_title_right, R.id.ll_me_balance_recharge, R.id.ll_me_balance_rollout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_me_balance_query:
                break;
            case R.id.tv_title_right:
                Me_Balance_Detail.launch(mContext);
                break;
            case R.id.ll_me_balance_recharge:
                Me_Balance_recharge.launch(mContext);
                break;
            case R.id.ll_me_balance_rollout:
                Me_Balance_Rollout.launch(mContext);
                break;
        }
    }


}
