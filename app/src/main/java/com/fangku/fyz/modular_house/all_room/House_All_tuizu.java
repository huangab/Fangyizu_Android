package com.fangku.fyz.modular_house.all_room;/**
 * Created by 67343 on 2016/8/4.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.widget.AllListView;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_Tuizu;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Shouzu;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Tuizu_Rent;
import com.fangku.fyz.util.MyBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 退租
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_All_tuizu extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.imageView)
    ImageView mImageView;

    @Bind(R.id.lv_house_all_tuizhu)
    AllListView mLvHouseAllTuizhu;
    @Bind(R.id.et_house_all_tuizu_remark)
    EditText mEtHouseAllTuizuRemark;

    Adapter_House_All_Tuizu mAdapter;
    Bean_House_All_Shouzu mBean;
    List<Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity> mList = new ArrayList<>();

    String contractId;
    String houseId;
    Intent intent;
    @Bind(R.id.tv_house_all_tuizu_money)
    TextView mTvHouseAllTuizuMoney;
    @Bind(R.id.tv_house_all_tuizu_moneys)
    TextView mTvHouseAllTuizuMoneys;
    @Bind(R.id.tv_house_all_tuizu_enddates)
    TextView mTvHouseAllTuizuEnddates;
    @Bind(R.id.tv_house_all_tuizu_startdate)
    TextView mTvHouseAllTuizuStartdate;
    @Bind(R.id.tv_house_all_tuizu_enddate)
    TextView mTvHouseAllTuizuEnddate;

    public static void launch(Context mContext, String roomId, String HouseId, String contractId) {
        Intent mIntent = new Intent(mContext, House_All_tuizu.class);
        mIntent.putExtra("roomId", roomId);
        mIntent.putExtra("HouseId", HouseId);
        mIntent.putExtra("contractId", contractId);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_tuizu;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("退租");
        mImageView.setVisibility(View.GONE);
        mLoadingDialog.show(mContext);
        //注册EventBus
        EventBus.getDefault().register(this);

        //获取当前时间
        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
        mTvHouseAllTuizuEnddates.setText(date);

        intent = getIntent();
        contractId = intent.getStringExtra("contractId");

        mAdapter = new Adapter_House_All_Tuizu(House_All_tuizu.this, mList);
        mLvHouseAllTuizhu.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("contractId", contractId);

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.RentFee, map)
                .execute(new Bean_Callback<Bean_House_All_Tuizu_Rent>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_All_Tuizu_Rent response, String message) {

                        houseId = response.getData().getRe().getHouseId();
                        mList.clear();
                        mList.addAll(response.getData().getRde());
                        mAdapter.notifyDataSetChanged();
                        //拿到押金进行计算
                        double yj = 0;
                        for (int i = 0; i < response.getData().getRde().size(); i++) {
                            if ("押金".equals(response.getData().getRde().get(i).getCostName())) {
                                yj = response.getData().getRde().get(i).getTotalCost();
                            }
                        }
                        double money = response.getData().getRe().getCost() - yj;
                        mTvHouseAllTuizuMoney.setText(String.valueOf(money));
                        mTvHouseAllTuizuMoneys.setText(String.valueOf(money));
                        mTvHouseAllTuizuStartdate.setText(response.getData().getRe().getStartDate());
                        mTvHouseAllTuizuEnddate.setText(response.getData().getRe().getEndDate());
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });

    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.btn_back, R.id.ll_house_all_tuizu_add, R.id.btn_house_all_tuizu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_house_all_tuizu_add:
                House_All_AddCost.launch(mContext);
                break;
            case R.id.btn_house_all_tuizu:
                saveRefundRent();
                break;
        }
    }

    /**
     * 退租接口
     */
    public void saveRefundRent() {
        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> map = new HashMap<>();

        map.put("roomId", intent.getStringExtra("roomId"));
        map.put("houseId", houseId);
        map.put("contractId", contractId);
        httpUtil.doPost(Url_final.SAVE_RENT, map, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                BaseResponse baseResponse = JsonMananger.jsonToBean(result, BaseResponse.class);
                if (baseResponse != null) {
                    if ("200".equals(baseResponse.getCode())) {
                        ToastUtil.showShort(mContext, baseResponse.getMessage());
                        House_All_Main.isNeedUpdate = true;
                        Fragment_House.isUpdate = true;
                        finish();
                    } else {
                        ToastUtil.showShort(mContext, baseResponse.getMessage());
                    }
                } else {
                    ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
                }
            }

            @Override
            public void onFailed(Exception e) {
                ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
            }
        });
    }

    /**
     * 接收eventbus传回来的数据
     *
     * @param event 根据这个实体列进行获取
     */
    @Subscribe
    public void onEventMainThread(Bean_House_All_Tuizu_Rent.DataEntity.RdeEntity event) {
        mList.add(event);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
