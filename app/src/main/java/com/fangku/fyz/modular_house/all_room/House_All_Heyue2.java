package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.widget.AllListView;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_Heyue2_MoneyList;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Contract;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 合约2
 * Created by bowen.ye
 * Date: 2016/9/23
 * Time: 20:41
 */
public class House_All_Heyue2 extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_heyue_qizuriqi)
    TextView mTvHeyueQizuriqi;
    @Bind(R.id.ll_heyue_qizuriqi)
    LinearLayout mLlHeyueQizuriqi;
    @Bind(R.id.et_heyue_zuqi)
    EditText mEtHeyueZuqi;
    @Bind(R.id.tv_heyue_jiesuriqi)
    TextView mTvHeyueJiesuriqi;
    @Bind(R.id.ll_heyue_jiesuriqi)
    LinearLayout mLlHeyueJiesuriqi;
    @Bind(R.id.ll_heyue_add)
    LinearLayout mLlHeyueAdd;
    @Bind(R.id.ll_heyue_delete)
    LinearLayout mLlHeyueDelete;
    @Bind(R.id.lv_qitfeiyong)
    AllListView mLvQitfeiyong;
    @Bind(R.id.et_heyue_zujin)
    EditText mEtHeyueZujin;
    @Bind(R.id.et_heyue_qitafeiyong)
    EditText mEtHeyueQitafeiyong;
    @Bind(R.id.et_heyue_chaobiaotixing)
    EditText mEtHeyueChaobiaotixing;
    @Bind(R.id.sc_heyue)
    ScrollView mScHeyue;
    @Bind(R.id.bt_heyue2_next)
    Button mBtHeyue2Next;
    @Bind(R.id.bt_heyue2_delete)
    Button mBtHeyue2Delete;
    @Bind(R.id.bt_heyue2_cancel)
    Button mBtHeyue2Cancel;

    Bean_House_All_Contract bean_house_all_contract;
    Adapter_House_All_Heyue2_MoneyList adapter_money_list;
    List<Bean_House_All_RoomCharge> mList = new ArrayList<>();
    Bean_House_All_RoomCharge bean_House_All_RoomCharge;

    public static void launch(Context mContext, Bean_House_All_Contract bean_house_all_contract) {
        Intent mIntent = new Intent(mContext, House_All_Heyue2.class);
        mIntent.putExtra("bean", bean_house_all_contract);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_heyue2;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("合约");
        mBtHeyue2Next.setText("退出合约");
        mEtHeyueZuqi.setEnabled(false);
        mEtHeyueZujin.setEnabled(false);
        mEtHeyueQitafeiyong.setEnabled(false);
        mEtHeyueChaobiaotixing.setEnabled(false);

        Intent mIntent = getIntent();
        bean_house_all_contract = (Bean_House_All_Contract) mIntent.getSerializableExtra("bean");
        mTvHeyueQizuriqi.setText(bean_house_all_contract.getData().getHtxx().getStartDate());
        mTvHeyueJiesuriqi.setText(bean_house_all_contract.getData().getHtxx().getEndDate());
        mEtHeyueZujin.setText(String.valueOf(bean_house_all_contract.getData().getHtxx().getRentCycle()));
        mEtHeyueQitafeiyong.setText(String.valueOf(bean_house_all_contract.getData().getHtxx().getChargeCycle()));
        mEtHeyueChaobiaotixing.setText(String.valueOf(bean_house_all_contract.getData().getHtxx().getWarmDay()));
        //计算起始日期到结束日期的时间
        int date = 0;
        int year = Integer.parseInt(bean_house_all_contract.getData().getHtxx().getEndDate().substring(0, 4)) - Integer.parseInt(bean_house_all_contract.getData().getHtxx().getStartDate().substring(0, 4));
        if (year == 0) {
            date = Integer.parseInt(bean_house_all_contract.getData().getHtxx().getEndDate().substring(5, 7)) - Integer.parseInt(bean_house_all_contract.getData().getHtxx().getStartDate().substring(5, 7));
        } else if (year == 1) {
            date = 12 - Integer.parseInt(bean_house_all_contract.getData().getHtxx().getStartDate().substring(5, 7)) + Integer.parseInt(bean_house_all_contract.getData().getHtxx().getEndDate().substring(5, 7));
        } else if (year == 2) {
            date = 12 - Integer.parseInt(bean_house_all_contract.getData().getHtxx().getStartDate().substring(5, 7)) + Integer.parseInt(bean_house_all_contract.getData().getHtxx().getEndDate().substring(5, 7) + 12);
        }
        mEtHeyueZuqi.setText(String.valueOf(date));
        int size = bean_house_all_contract.getData().getZjmx().size();
        for (int i = 0; i < size; i++) {
            bean_House_All_RoomCharge = new Bean_House_All_RoomCharge();
            bean_House_All_RoomCharge.setCostName(bean_house_all_contract.getData().getZjmx().get(i).getCostName());
            bean_House_All_RoomCharge.setChargeType(bean_house_all_contract.getData().getZjmx().get(i).getChargeType());
            bean_House_All_RoomCharge.setCostPrice(bean_house_all_contract.getData().getZjmx().get(i).getCostPrice());
            bean_House_All_RoomCharge.setCostUnit(bean_house_all_contract.getData().getZjmx().get(i).getCostUnit());
            mList.add(bean_House_All_RoomCharge);
        }
        adapter_money_list = new Adapter_House_All_Heyue2_MoneyList(House_All_Heyue2.this, mList);
        mLvQitfeiyong.setAdapter(adapter_money_list);

    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.bt_heyue2_next, R.id.bt_heyue2_delete, R.id.bt_heyue2_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_heyue2_next:
                ActivityManager.getInstance().removeActivity(House_All_Heyue2.class);
                ActivityManager.getInstance().removeActivity(House_All_Heyue.class);
                break;
            case R.id.bt_heyue2_delete:
                break;
            case R.id.bt_heyue2_cancel:
                break;
        }
    }
}
