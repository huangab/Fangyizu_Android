package com.fangku.fyz.modular_house.add_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_GridView_DataMoney;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加费用
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_Add_OtherMoney extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.ngv_money_add)
    NoScrollGridView mGridView;
    @Bind(R.id.et_addMoney_Name)
    EditText mEtAddMoneyName;
    @Bind(R.id.et_addMoney_yajing)
    EditText mEtAddMoneyYajing;
    @Bind(R.id.tv_addMoney_yajingDanwei)
    TextView mTvAddMoneyYajingDanwei;
    @Bind(R.id.et_addMoney_max)
    EditText mEtAddMoneyMax;
    @Bind(R.id.et_addMoney_min)
    EditText mEtAddMoneyMin;
    @Bind(R.id.bt_addMoney_OK)
    Button mBtAddMoneyOK;

    List<Bean_ListData> dataList = new ArrayList<>();
    Adapter_GridView_DataMoney mAdapter;

    ArrayList<String> mNameData = new ArrayList<>();

    @Bind(R.id.ll_add_money_bottomll)
    LinearLayout mLlAddMoneyBottomll;
    @Bind(R.id.imageView)
    ImageView mImageView;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, House_Add_OtherMoney.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_add_money;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("新增费用");
        mImageView.setVisibility(View.GONE);
        Intent intent = getIntent();

        mNameData = intent.getStringArrayListExtra("name");


        mAdapter = new Adapter_GridView_DataMoney(this, dataList, mEtAddMoneyName, mTvAddMoneyYajingDanwei, mLlAddMoneyBottomll);
        mGridView.setAdapter(mAdapter);


    }


    @Override
    public void getData() {
        mLoadingDialog.show(mContext);
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doPost(Url_final.GET_BASELIST + "ChargeType/" + "false", new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Log.i("test", "Success:" + result);
                    List<Bean_ListData> datas = JsonMananger.jsonToList(result, Bean_ListData.class);
                    if (datas != null && datas.size() != 0) {
                        dataList.addAll(datas);
                        Bean_ListData qita = new Bean_ListData();
                        qita.setName("其他");
                        qita.setAttr3("元/月");
                        qita.setAttr4("1");
                        dataList.add(qita);
                        dataList.get(0).setIsCheck(true);
                        mAdapter.notifyDataSetInvalidated();
                        upView(dataList.get(0));
                    }
                } else {
                    ToastUtil.showShort(mContext, "获取费用失败");
                    mLoadingDialog.cancel();
                    finish();
                }
                mLoadingDialog.cancel();
            }

            @Override
            public void onFailed(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "错误信息:" + e.getMessage());
                Log.i("test", "onFailed:" + e.getMessage());
                mLoadingDialog.cancel();

            }
        });
    }

    private void upView(Bean_ListData data) {
        if (data == null) {
            return;
        }
        mEtAddMoneyName.setText(data.getName());
        mTvAddMoneyYajingDanwei.setText(data.getAttr3().toString());
    }


    @OnClick({R.id.btn_back, R.id.bt_addMoney_OK})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_addMoney_OK:
                go();
                break;
        }
    }

    private void go() {

        String name = mEtAddMoneyName.getText().toString();
        String danjia = mEtAddMoneyYajing.getText().toString();
        String danwei = mTvAddMoneyYajingDanwei.getText().toString();
        String min = mEtAddMoneyMin.getText().toString();
        if (name.equals("")) {
            ToastUtil.showShort(mContext, "你还未输入费用名!");
            return;
        }
        danjia = danjia.equals("") ? danjia = "0" : danjia;
        min = min.equals("") ? min = "0" : min;


        if (mNameData != null) {
            for (int i = 0; i < mNameData.size(); i++) {
                if (name.equals(mNameData.get(i))) {
                    ToastUtil.showShort(mContext, "已经存在的类型,不能重复添加!");
                    return;
                }
            }
        }
        Bean_House_All_RoomCharge charge = new Bean_House_All_RoomCharge();
        charge.setCostName(name);
        charge.setCostPrice(Double.valueOf(danjia));
        charge.setCostUnit(danwei);
        charge.setOperate("A");

        if (mLlAddMoneyBottomll.getVisibility() == View.GONE) {
            charge.setMinValue(0);
            charge.setChargeType("1");
        } else {
            charge.setMinValue(Integer.valueOf(min));
            charge.setChargeType("2");

        }


        Intent intent = new Intent();
        intent.putExtra("list", charge);
        //通过Intent对象返回结果，调用setResult方法
        setResult(Static_Conmom.MONEY_RESULT, intent);

        finish();

    }
}
