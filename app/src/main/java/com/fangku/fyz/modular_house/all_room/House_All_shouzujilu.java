package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_History;
import com.fangku.fyz.modular_house.bean.Bean_House_All_History;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 收租记录
 * Created by   jie.wang
 * Date: 2016/8/8
 * Time: 9:42
 */
public class House_All_shouzujilu extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.lv_history_listview)
    ListView mLvHistoryListview;

    private String mHouseID;
    private String mRoomID;

    List<Bean_House_All_History.DataBean> mData = new ArrayList<>();

    Adapter_House_All_History mAdapter;


    public static void launch(Context mContext, String houseId, String roomId) {
        Intent mIntent = new Intent(mContext, House_All_shouzujilu.class);
        mIntent.putExtra("houseID", houseId);
        mIntent.putExtra("roomID", roomId);

        mContext.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.house_all_shouzujilu;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mTvTitle.setText("历史收租记录");
        mAdapter = new Adapter_House_All_History(this, mData);
        mLvHistoryListview.setAdapter(mAdapter);
        Intent intent = getIntent();
        mHouseID = intent.getStringExtra("houseID");
        mRoomID = intent.getStringExtra("roomID");

    }

    @Override
    public void getData() {

        mLoadingDialog.show(mContext);

        mData.clear();
        Map<String, String> map = new HashMap<>();
        map.put("houseId", mHouseID);
        map.put("roomId", mRoomID);


        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.QUERY_HIS, map)
                .execute(new BeanList_Callback<Bean_House_All_History>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_All_History response, String message) {
                        mData.addAll(response.getData());
                        mAdapter.notifyDataSetChanged();

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


}
