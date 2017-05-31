package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_Zuke_Expandablelist;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Roomer_List;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 租客
 * Created by   jie.wang
 * Date: 2016/8/8
 * Time: 10:39
 */
public class House_All_zuke extends MyBaseActivity {

    Adapter_House_All_Zuke_Expandablelist mAdapter;
    List<Bean_House_All_Roomer_List.DataEntity> mList = new ArrayList<>();
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Bind(R.id.elv_house_all_zuke)
    ExpandableListView mElvHouseAllZuke;

    Context mContext = House_All_zuke.this;

    public static void launch(Context mContext, String contractId) {
        Intent mIntent = new Intent(mContext, House_All_zuke.class);
        mIntent.putExtra("contractId", contractId);
        mContext.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.house_all_zuke;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("租客");
        mTvTitleRight.setText("新增租客");
        mTvTitleRight.setVisibility(View.VISIBLE);

        mAdapter = new Adapter_House_All_Zuke_Expandablelist(mContext, mList);
        mElvHouseAllZuke.setAdapter(mAdapter);
        mElvHouseAllZuke.setGroupIndicator(null);
        mElvHouseAllZuke.setOnGroupExpandListener(groupPosition -> {
            //当点击某一项 关闭其他项
            for (int i = 0; i < mAdapter.getGroupCount(); i++) {
                if (groupPosition != i) {
                    mElvHouseAllZuke.collapseGroup(i);
                }
            }
        });

    }

    @Override
    public void getData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        mLoadingDialog.show(mContext);
        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> map = new HashMap<>();
        map.put("contractId", intent.getStringExtra("contractId"));
        httpUtil.doPost(Url_final.QUERY_ROOMER, map, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mLoadingDialog.cancel();
                Log.i("a", "onSuccess: " + result);
                Bean_House_All_Roomer_List roomer = JsonMananger.jsonToBean(result, Bean_House_All_Roomer_List.class);
                if (roomer != null) {
                    if ("200".equals(roomer.getCode())) {
                        mList.clear();
                        mList.addAll(roomer.getData());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.showShort(mContext, roomer.getMessage());
                    }
                } else {
                    ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
                }
            }

            @Override
            public void onFailed(Exception e) {
                mLoadingDialog.cancel();
                ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
            }
        });
    }

    @OnClick({R.id.btn_back, R.id.tv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title_right:
                House_All_zuke_message.launch(mContext, false, null, null, null, null, null, null, mList.get(0).getRoomId(), mList.get(0).getHouseId(), mList.get(0).getContractId(), mList.get(0).getLandlordId(), null);
                break;
        }
    }
}
