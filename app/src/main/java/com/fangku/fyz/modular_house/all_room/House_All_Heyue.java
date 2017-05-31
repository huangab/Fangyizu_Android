package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.commonlibrary.widget.AllListView;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_House_Heyue;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Contract;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 合约
 * Created by bowen.ye
 * Date: 2016/9/23
 * Time: 20:41
 */
public class House_All_Heyue extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_heyue_get_house)
    TextView mTvHeyueGetHouse;
    @Bind(R.id.tv_heyue_get_room)
    TextView mTvHeyueGetRoom;
    @Bind(R.id.ll_heyue_saomiao)
    LinearLayout mLlHeyueSaomiao;
    @Bind(R.id.iv_heyeu_zhengmian)
    ImageView mIvHeyeuZhengmian;
    @Bind(R.id.iv_heyeu_fangmian)
    ImageView mIvHeyeuFangmian;
    @Bind(R.id.et_heyue_realname)
    EditText mEtHeyueRealname;
    @Bind(R.id.et_heyue_card_num)
    EditText mEtHeyueCardNum;
    @Bind(R.id.et_heyue_phone)
    EditText mEtHeyuePhone;
    @Bind(R.id.et_heyue_work_map)
    EditText mEtHeyueWorkMap;
    @Bind(R.id.rb_heyue_man)
    RadioButton mRbHeyueMan;
    @Bind(R.id.rb_heyue_woman)
    RadioButton mRbHeyueWoman;
    @Bind(R.id.et_heyue_money1)
    EditText mEtHeyueMoney1;
    @Bind(R.id.et_heyue_money2)
    EditText mEtHeyueMoney2;
    @Bind(R.id.lv_heyue)
    AllListView mLvHeyue;

    Adapter_House_Heyue mAdapter;
    List<Bean_House_All_Contract.DataEntity.CbxxEntity> mBiao = new ArrayList<>();

    String contractId;
    String houseName;
    String roomName;

    Bean_House_All_Contract bean_house_all_contract;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.sc_heyue)
    ScrollView mScHeyue;
    @Bind(R.id.bt_heyue_next)
    Button mBtHeyueNext;

    public static void launch(Context mContext, String contractId, String houseName, String roomName) {
        Intent mIntent = new Intent(mContext, House_All_Heyue.class);
        mIntent.putExtra("contractId", contractId);
        mIntent.putExtra("houseName", houseName);
        mIntent.putExtra("roomName", roomName);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_heyue;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        mBtHeyueNext.setText("下一页");
        contractId = intent.getStringExtra("contractId");
        houseName = intent.getStringExtra("houseName");
        roomName = intent.getStringExtra("roomName");
        mImageView.setVisibility(View.GONE);
        mTvTitle.setText("合约");
        mEtHeyueRealname.setEnabled(false);
        mEtHeyueCardNum.setEnabled(false);
        mEtHeyuePhone.setEnabled(false);
        mEtHeyueWorkMap.setEnabled(false);
        mRbHeyueMan.setEnabled(false);
        mRbHeyueWoman.setEnabled(false);
        mEtHeyueMoney1.setEnabled(false);
        mEtHeyueMoney2.setEnabled(false);

        //抄表信息写入
        Bean_House_All_Contract.DataEntity.CbxxEntity hydropower = new Bean_House_All_Contract.DataEntity.CbxxEntity();
        hydropower.setMeterName("水表");
        hydropower.setMeterType("E");
        mBiao.add(hydropower);
        Bean_House_All_Contract.DataEntity.CbxxEntity hydropower2 = new Bean_House_All_Contract.DataEntity.CbxxEntity();
        hydropower2.setMeterName("电表");
        hydropower2.setMeterType("W");
        mBiao.add(hydropower2);
        mScHeyue.smoothScrollTo(0, 0);
        mAdapter = new Adapter_House_Heyue(House_All_Heyue.this, mBiao);
        mLvHeyue.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        mLoadingDialog.show(mContext);


        Map<String, String> map = new HashMap<>();
        map.put("contractId", contractId);

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.ContractById, map)
                .execute(new BeanList_Callback<Bean_House_All_Contract>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_All_Contract response, String message) {
                        mTvHeyueGetHouse.setText(houseName);
                        mTvHeyueGetRoom.setText(roomName);
                        mEtHeyueRealname.setText(bean_house_all_contract.getData().getHtxx().getRoomerName());
                        mEtHeyueCardNum.setText(bean_house_all_contract.getData().getHtxx().getRoomerCardId());
                        mEtHeyueRealname.setText(bean_house_all_contract.getData().getHtxx().getRoomerName());
                        mEtHeyuePhone.setText(bean_house_all_contract.getData().getHtxx().getRoomerMobile());
                        mEtHeyueMoney1.setText(String.valueOf(bean_house_all_contract.getData().getHtxx().getRent()));
                        mEtHeyueMoney2.setText(String.valueOf(bean_house_all_contract.getData().getHtxx().getDeposit()));
                        //写入水电表
                        mBiao.clear();
                        mBiao.addAll(bean_house_all_contract.getData().getCbxx());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });

    }


    @OnClick({R.id.btn_back, R.id.bt_heyue_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_heyue_next:
                House_All_Heyue2.launch(mContext, bean_house_all_contract);
                break;
        }
    }


}
