package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Tuizu_Rent;
import com.fangku.fyz.modular_house.dialog.Dialog_All_AddCost_Type;
import com.fangku.fyz.util.MyBaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加费用
 * Created by bowen.ye
 * Date: 2016/9/5
 * Time: 11:18
 */
public class House_All_AddCost extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_house_all_addcost_type)
    TextView mTvHouseAllAddcostType;
    @Bind(R.id.et_house_all_addcost_type)
    EditText mEtHouseAllAddcostType;
    @Bind(R.id.et_house_all_addcost_money)
    EditText mEtHouseAllAddcostMoney;

    Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity mBean;

    Context mContext = House_All_AddCost.this;

    Dialog_All_AddCost_Type dialog;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, House_All_AddCost.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_addcost;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("添加费用");
    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.ll_house_all_addcost_type, R.id.btn_house_all_addcost})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_house_all_addcost_type:
                dialog = new Dialog_All_AddCost_Type(House_All_AddCost.this, mTvHouseAllAddcostType, mEtHouseAllAddcostType);
                dialog.builder().show();
                break;
            case R.id.btn_house_all_addcost:
                if (dialog == null) {
                    ToastUtil.showShort(mContext, "请先选择类型");
                } else {
                    mBean = dialog.getBean();
                    mBean.setTotalCost(Double.parseDouble(mEtHouseAllAddcostMoney.getText().toString().trim()));
                    mBean.setCostPrice(Double.parseDouble(mEtHouseAllAddcostMoney.getText().toString().trim()));
                    if (mBean.getCostPrice()==0) {
                        ToastUtil.showShort(mContext, "请先填写金额");
                    } else {
                        //发送数据
                        EventBus.getDefault().post(mBean);
                        finish();
                    }
                }
                break;
        }
    }

}
