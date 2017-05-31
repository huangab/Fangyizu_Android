package com.fangku.fyz.modular_me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_me.adapter.Adapter_Cardpackage;
import com.fangku.fyz.modular_me.bean.Bean_Me_Cardpag;
import com.fangku.fyz.util.MyBaseActivity;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 卡卷包
 * Created by jie.wang
 * Date: 2016/7/15
 * Time: 15:57
 */
public class Me_Cardpackage extends MyBaseActivity {
    @Bind(R.id.lv_me_cardpackage)
    ListView mLvMeCardpackage;
    @Bind(R.id.sv_me_cardpackage)
    SpringView mSvMeCardpackage;

    List<Bean_Me_Cardpag.DataBean> mList = new ArrayList<>();

    Context mContext = Me_Cardpackage.this;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;

    private Adapter_Cardpackage mAdapter;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, Me_Cardpackage.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.me_cardpackage;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("我的卡券包");

        mAdapter = new Adapter_Cardpackage(Me_Cardpackage.this, mList);
        mLvMeCardpackage.setAdapter(mAdapter);
        mLvMeCardpackage.setDivider(null);
        mSvMeCardpackage.setType(SpringView.Type.FOLLOW);
        mSvMeCardpackage.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                mSvMeCardpackage.onFinishFreshAndLoad();

            }

            @Override
            public void onLoadmore() {
                ToastUtil.showShort(mContext, "上拉加载");

                mSvMeCardpackage.onFinishFreshAndLoad();


            }
        });
    }

    @Override
    public void getData() {
        mLoadingDialog.show(mContext);


        new PostUtil()
                .Post_Bean(Url_final.GET_CARDPAGER)
                .execute(new BeanList_Callback<Bean_Me_Cardpag>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_Me_Cardpag response, String message) {
                        mList.addAll(response.getData());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel500();

                    }

                });

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
