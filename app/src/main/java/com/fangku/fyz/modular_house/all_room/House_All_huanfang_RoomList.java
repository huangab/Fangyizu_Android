package com.fangku.fyz.modular_house.all_room;/**
 * Created by 67343 on 2016/9/27.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseActivity;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_huanfang_list_left;
import com.fangku.fyz.modular_house.adapter.Adapter_huanfang_list_right;
import com.fangku.fyz.modular_house.bean.Bean_House_Data;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 房间选择
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_All_huanfang_RoomList extends BaseActivity implements Adapter_huanfang_list_left.huangfangLeftMessage, Adapter_huanfang_list_right.huangfangRightMessage {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.lv_huanfang_list_left)
    ListView mLvHuanfangListLeft;
    @Bind(R.id.lv_huanfang_list_right)
    ListView mLvHuanfangListRight;
    @Bind(R.id.tv_house_all_huanfang_message)
    TextView mTvHouseAllHuanfangMessage;
    @Bind(R.id.pb_huanfang)
    ProgressBar mPbHuanfang;

    Adapter_huanfang_list_right rightAdapter;
    Adapter_huanfang_list_left leftAdapter;

    //    List<String> leftData = new ArrayList<>();
    List<Bean_House_Data.DataBean> leftData = new ArrayList<>();

    //    List<String> rigetData = new ArrayList<>();
    List<Bean_House_Type_Room.DataBean> rigetData = new ArrayList<>();

    String houseName = "";
    private String houseId;


    @Override
    public int bindLayout() {
        return R.layout.house_all_huanfang_roomlist;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mLoadingDialog.show(this, "正在加载数据，请稍候！");
        mTvTitle.setText("房间选择");
        leftAdapter = new Adapter_huanfang_list_left(this, leftData);
        mLvHuanfangListLeft.setAdapter(leftAdapter);
        leftAdapter.setHuangfangLeftMessage(this);

        rightAdapter = new Adapter_huanfang_list_right(this, rigetData);
        mLvHuanfangListRight.setAdapter(rightAdapter);
        rightAdapter.setHuangfangRightMessage(this);

        getHouseList();

    }

    private void getHouseList() {
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.GETHOUSEDATA)
                .execute(new BeanList_Callback<Bean_House_Data>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Data response, String message) {
                        leftData.addAll(response.getData());
                        leftAdapter.notifyDataSetInvalidated();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }

                    @Override
                    protected void onFailed(String message) {
                        super.onFailed(message);
                        finish();
                    }
                });

    }

    @Override
    public void getData() {

    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }

    @Override
    public void getMessage(Bean_House_Data.DataBean s) {
        rigetData.clear();
        mTvHouseAllHuanfangMessage.setVisibility(View.GONE);
        mPbHuanfang.setVisibility(View.VISIBLE);
        houseName = s.getHouseName();
        Map<String, String> map = new HashMap<>();
        map.put("houseId", s.getSysId());
        houseId = s.getSysId();
        mLoadingDialog.show(mContext);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.QUERY_NO_RENT_ROOM, map)
                .execute(new BeanList_Callback<Bean_House_Type_Room>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Type_Room response, String message) {
                        rigetData.addAll(response.getData());
                        if (rigetData.size() <= 0) {
                            mTvHouseAllHuanfangMessage.setVisibility(View.VISIBLE);
                        } else {
                            mTvHouseAllHuanfangMessage.setVisibility(View.GONE);
                        }
                        rightAdapter.notifyDataSetInvalidated();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }

                    @Override
                    protected void onFailed(String message) {
                        super.onFailed(message);
                        mPbHuanfang.setVisibility(View.GONE);
                        mTvHouseAllHuanfangMessage.setVisibility(View.VISIBLE);

                    }
                });

    }


    @Override
    public void getMessage2(Bean_House_Type_Room.DataBean s) {
        Intent intent = new Intent();
        intent.putExtra("data", houseName + "," + s.getRoomNo() + "室");
        intent.putExtra("houseId", houseId);
        intent.putExtra("roomId", s.getRoomId());
        setResult(CONTEXT_IGNORE_SECURITY, intent);
        finish();
    }

}
