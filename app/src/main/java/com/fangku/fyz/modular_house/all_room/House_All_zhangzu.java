package com.fangku.fyz.modular_house.all_room;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Contract;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 涨租
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_All_zhangzu extends MyBaseActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_house_all_zhangzu_rent)
    EditText mEtHouseAllZhangzuRent;
    @Bind(R.id.tv_house_all_zhangzu_date)
    TextView mTvHouseAllZhangzuDate;

    String houseId;
    String roomId;
    String rent;
    String deposit;
    String startDate;
    String endDate;
    String rentCycle;
    String chargeCycle;
    String contractId;
    String sysId;

    int mYear;
    int month;
    int day;

    public static void launch(Context mContext, String contractId) {
        Intent mIntent = new Intent(mContext, House_All_zhangzu.class);
        mIntent.putExtra("contractId", contractId);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_zhangzu;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("涨租");
        mLoadingDialog.show(mContext);
        Intent intent = getIntent();
        contractId = intent.getStringExtra("contractId");

        //获取当前时间
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        String date = mYear + "-" + month + "-" + day;
        mTvHouseAllZhangzuDate.setText(date);
    }

    @Override
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("contractId", contractId);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.ContractById, map)
                .execute(new Bean_Callback<Bean_House_All_Contract>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_All_Contract response, String message) {
                        sysId = response.getData().getZjxx().getSysId();
                        houseId = response.getData().getZjxx().getHouseId();
                        roomId = response.getData().getZjxx().getRoomId();
                        rent = String.valueOf(response.getData().getHtxx().getRent());
                        deposit = String.valueOf(response.getData().getHtxx().getDeposit());
                        startDate = response.getData().getHtxx().getStartDate();
                        endDate = response.getData().getHtxx().getEndDate();
                        rentCycle = String.valueOf(response.getData().getHtxx().getRentCycle());
                        chargeCycle = String.valueOf(response.getData().getHtxx().getChargeCycle());
                        mEtHouseAllZhangzuRent.setText(rent);
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });
    }


    /**
     * 涨租
     */
    public void zhangzu() {
        if (!"".equals(mEtHouseAllZhangzuRent.getText().toString())) {
            mLoadingDialog.show(mContext);
            Map<String, String> map = new HashMap<>();
            map.put("sysId", contractId);
            map.put("houseId", houseId);
            map.put("roomId", roomId);
            map.put("rent", mEtHouseAllZhangzuRent.getText().toString());
            map.put("deposit", deposit);
            map.put("startDate", mTvHouseAllZhangzuDate.getText().toString());
            map.put("endDate", endDate);
            map.put("rentCycle", rentCycle);
            map.put("chargeCycle", chargeCycle);

            PostUtil postUtil = new PostUtil();
            postUtil.Post_Bean(Url_final.SAVA_RISERENTCONTRACT, map)
                    .execute(new Bean_Callback<BeanResponse>(mContext) {
                        @Override
                        protected void onSuccess_Code200(BeanResponse response, String message) {
                            House_All_Main.isNeedUpdate = true;
                            Fragment_House.isUpdate = true;
                            finish();
                        }

                        @Override
                        protected void onOver() {
                            mLoadingDialog.cancel();
                        }
                    });

        } else {
            ToastUtil.showShort(mContext, "请输入涨租的租金");
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_house_all_zhangzu, R.id.tv_house_all_zhangzu_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_house_all_zhangzu:
                zhangzu();
                break;
            case R.id.tv_house_all_zhangzu_date:
                birthday();
                break;
        }
    }

    /**
     * 日期弹出框
     */
    public void birthday() {
        //创建DatePickerDialog对象
        DatePickerDialog dpd = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
                mYear = year;
                month = monthOfYear;
                day = dayOfMonth;
                //更新日期
                String mDate = mYear + "-" + (month + 1) + "-" + day;
                mTvHouseAllZhangzuDate.setText(mDate);
            }
        }, mYear, month, day);
        dpd.show();//显示DatePickerDialog组件
    }

}
