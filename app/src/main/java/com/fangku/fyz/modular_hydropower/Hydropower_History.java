package com.fangku.fyz.modular_hydropower;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 抄表历史
 * Created by bowen.ye
 * Date: 2016/8/13
 * Time: 11:12
 */
public class Hydropower_History extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.lv_hydropower_history)
    ListView mLvHydropowerHistory;

    List<Bean_Hydropower.DataEntity> mList = new ArrayList<>();
    Adapter_Hydropower_List_History mAdapter;

    String houseId;
    String roomId;
    String roomNo;
    String meterType;

    Context mContext = Hydropower_History.this;

    public static void launch(Context mContext, String houseId, String roomId, String roomNo, String meterType) {
        Intent mIntent = new Intent(mContext, Hydropower_History.class);
        mIntent.putExtra("houseId", houseId);
        mIntent.putExtra("roomId", roomId);
        mIntent.putExtra("roomNo", roomNo);
        mIntent.putExtra("meterType", meterType);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.hydropower_history;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        houseId = mIntent.getStringExtra("houseId");
        roomId = mIntent.getStringExtra("roomId");
        roomNo = mIntent.getStringExtra("roomNo");
        meterType = mIntent.getStringExtra("meterType");
        if (meterType.equals("E")) {
            mTvTitle.setText("电表 (" + roomNo + ")");
        } else {
            mTvTitle.setText("水表 (" + roomNo + ")");
        }

        mAdapter = new Adapter_Hydropower_List_History(this, mList);
        mLvHydropowerHistory.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        queryHisScalebyRoomId();
    }

    public void queryHisScalebyRoomId() {
        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> mMap = new HashMap<>();
        mMap.put("houseId", houseId);
        mMap.put("roomId", roomId);
        mMap.put("meterType", meterType);
        httpUtil.doPost(Url_final.HISTORY_HY, mMap, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                Bean_Hydropower beanHydropower = JsonMananger.jsonToBean(result, Bean_Hydropower.class);
                if (beanHydropower != null) {
                    if ("200".equals(beanHydropower.getCode())) {
                        mList.clear();
                        mList.addAll(beanHydropower.getData());
                        mAdapter.notifyDataSetChanged();
                    }else{
                        ToastUtil.showShort(mContext,beanHydropower.getMessage());
                    }
                }else{
                    ToastUtil.showShort(mContext,"网络访问失败");
                }
            }

            @Override
            public void onFailed(Exception e) {
                ToastUtil.showShort(mContext,"网络访问失败");
            }
        });
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}
