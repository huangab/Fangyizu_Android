package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.fyz.R;
import com.fangku.fyz.util.MyBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 单个费用修改
 * Created by bowen.ye
 * Date: 2016/10/12
 * Time: 9:30
 */

public class House_All_DCost extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_house_all_cost_cost)
    EditText mEtHouseAllCostCost;
    @Bind(R.id.et_house_all_cost_danjia)
    EditText mEtHouseAllCostDanjia;

    int position;

    public static void launch(Context mContext,String cost,String danjia,int position) {
        Intent mIntent = new Intent(mContext, House_All_DCost.class);
        mIntent.putExtra("cost", cost);
        mIntent.putExtra("danjia", danjia);
        mIntent.putExtra("position", position);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_cost;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        Intent intent=getIntent();
        mEtHouseAllCostCost.setText(intent.getStringExtra("cost"));
        mEtHouseAllCostDanjia.setText(intent.getStringExtra("danjia"));
        position=intent.getIntExtra("position",1);

        mTvTitle.setText(House_All_Shouzu.mList.get(position).getCostName());
    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.btn_house_all_cost})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_house_all_cost:
                House_All_Shouzu.mList.get(position).setCostPrice(Double.parseDouble(mEtHouseAllCostDanjia.getText().toString()));
                House_All_Shouzu.mList.get(position).setTotalCost(Double.parseDouble(mEtHouseAllCostCost.getText().toString()));
                finish();
                break;
        }
    }
}
