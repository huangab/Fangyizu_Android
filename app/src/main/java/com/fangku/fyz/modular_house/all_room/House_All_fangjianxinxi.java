package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.commonlibrary.widget.AllListView;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_Money_list;
import com.fangku.fyz.modular_house.add_room.House_Add_OtherMoney;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.modular_house.bean.Bean_Room_message;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.widget.Dialog_Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 房间信息
 * Created by   jie.wang
 * Date: 2016/9/7
 * Time: 10:52
 */
public class House_All_fangjianxinxi extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_right_big_image)
    ImageView mIvRightBigImage;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.tv_fangjianxinxi_house_name)
    TextView mTv_HouseName;
    @Bind(R.id.et_fangjianxinxi_room_name)
    EditText mEt_RoomName;
    @Bind(R.id.et_fangjianxinxi_woshi)
    EditText mEt_Woshi;
    @Bind(R.id.et_fangjianxinxi_ting)
    EditText mEt_Ting;
    @Bind(R.id.et_fangjianxinxi_weishengjian)
    EditText mEt_Weishengjian;
    @Bind(R.id.et_fangjianxinxi_yajinshu)
    EditText mEt_Yajinshu;
    @Bind(R.id.et_fangjianxinxi_fuqianshu)
    EditText mEt_Fuqianshu;
    @Bind(R.id.et_fangjianxinxi_louceng)
    EditText mEt_Louceng;
    @Bind(R.id.lv_fangjianxinxi)
    AllListView mLv_av;
    @Bind(R.id.sc_fangjianxinxi)
    ScrollView mSc_;
    @Bind(R.id.bt_fangjianxinxi_ok)
    Button mBt_Ok;
    @Bind(R.id.bt_fangjianxinxi_lishiheyue)
    Button mBt_Lishiheyue;
    @Bind(R.id.bt_fangjianxinxi_delete)
    Button mBt_Delete;
    @Bind(R.id.bt_fangjianxinxi_cancel)
    Button mBt_Cancel;
    @Bind(R.id.ll_fangjianxinxi_delete_layout)
    LinearLayout mLl_DeleteLayout;
    @Bind(R.id.et_fangjianxinxi_zujin)
    EditText mEtFangjianxinxiZujin;
    @Bind(R.id.ll_feiyong_yincang)
    LinearLayout mLlFeiyongYincang;
    private List<Bean_House_All_RoomCharge> mBiao = new ArrayList<>();

    private Adapter_Money_list mAdapter;

    private boolean isDelete;

    private String mHouseName;
    private String mHouseId;
    private String mRoomId;
    private String mRoomName;
    private double mRent;

    private Boolean mIsshow;


    public static void launch(Context mContext, String houseID, String roomID, String houseName, String roomName, Boolean isshow) {
        Intent mIntent = new Intent(mContext, House_All_fangjianxinxi.class);
        mIntent.putExtra("houseName", houseName);
        mIntent.putExtra("roomId", roomID);
        mIntent.putExtra("roomName", roomName);
        mIntent.putExtra("houseID", houseID);
        mIntent.putExtra("show", isshow);//是否显示费用列表
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_fangjianxinxi;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mLoadingDialog.show(mContext);
        mTvTitle.setText("房间信息");
        Intent intent = getIntent();

        mHouseName = intent.getStringExtra("houseName");
        mRoomId = intent.getStringExtra("roomId");
        mRoomName = intent.getStringExtra("roomName");
        mHouseId = intent.getStringExtra("houseID");
        mIsshow = intent.getExtras().getBoolean("show");

        mTv_HouseName.setText(mHouseName);
        mEt_RoomName.setText(mRoomName);

        if (mIsshow) {
            mLlFeiyongYincang.setVisibility(View.VISIBLE);
        } else {
            mLlFeiyongYincang.setVisibility(View.GONE);
        }
        mImageView.setVisibility(View.GONE);//隐藏title底部横线


        mSc_.smoothScrollTo(0, 0);


        mAdapter = new Adapter_Money_list(House_All_fangjianxinxi.this, mBiao);
        mLv_av.setAdapter(mAdapter);

    }

    @Override
    public void getData() {


        Map<String, String> map = new HashMap<>();
        map.put("houseId", mHouseId);
        map.put("sysId", mRoomId);
        new PostUtil<>().Post_Bean(Url_final.GET_ROOM_BY_ID, map).execute(new Bean_Callback<Bean_Room_message>(mContext) {
            @Override
            protected void onSuccess_Code200(Bean_Room_message response, String message) {
                mEt_Woshi.setText(response.getData().getFj().getRoomNumber() + "");
                mEt_Weishengjian.setText(response.getData().getFj().getToiletNumber() + "");
                mEt_Ting.setText(response.getData().getFj().getHallNumber() + "");
                mEt_Yajinshu.setText(response.getData().getFj().getDepositNumber() + "");
                mEt_Fuqianshu.setText(response.getData().getFj().getPayNumber() + "");
                mEt_Louceng.setText(response.getData().getFj().getHouseLeve() + "");
                mEtFangjianxinxiZujin.setText(response.getData().getFj().getRent() + "");
                mRent = response.getData().getFj().getRent();

                List<Bean_Room_message.DataBean.FyBean> fyBean = new ArrayList<Bean_Room_message.DataBean.FyBean>();
                fyBean.addAll(response.getData().getFy());
                //因为是2个不同的实体类 改动代价太大 所以就直接  使用 取出来存进去的方法
                if (fyBean != null) {
                    for (int i = 0; i < fyBean.size(); i++) {

                        Bean_House_All_RoomCharge charge = new Bean_House_All_RoomCharge();


                        charge.setChargeType(fyBean.get(i).getChargeType());
                        charge.setContractId((String) fyBean.get(i).getContractId());
                        charge.setCostName(fyBean.get(i).getCostName());
                        charge.setCostPrice(fyBean.get(i).getCostPrice());
                        charge.setCostUnit(fyBean.get(i).getCostUnit());
                        charge.setHouseId(fyBean.get(i).getHouseId());
                        charge.setMaxValue((Integer) fyBean.get(i).getMaxValue());
                        charge.setMinValue(fyBean.get(i).getMinValue());
                        charge.setMeterCode((String) fyBean.get(i).getMeterCode());
                        charge.setRoomId(fyBean.get(i).getRoomId());
                        charge.setSysId(fyBean.get(i).getSysId());

                        mBiao.add(charge);

                    }


                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            protected void onOver() {
                mLoadingDialog.cancel();
            }
        });


    }


    @OnClick({R.id.btn_back, R.id.ll_fangjian_add, R.id.ll_fangjian_delete, R.id.iv_right_big_image, R.id.bt_fangjianxinxi_ok, R.id.bt_fangjianxinxi_lishiheyue, R.id.bt_fangjianxinxi_delete, R.id.bt_fangjianxinxi_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:

                finish();

                break;
            case R.id.iv_right_big_image:


                break;
            case R.id.bt_fangjianxinxi_ok:
                Dialog_Show.showTwoButton(mContext, "温馨提醒", "是否提交本次更改?", "取消", "确定", result -> {
                    if (result.equals("2")) {
                        updateRoom();
                    }
                });


                break;
            case R.id.bt_fangjianxinxi_lishiheyue:


                break;
            case R.id.bt_fangjianxinxi_delete:
                click_Delete_Btn();


                break;


            case R.id.bt_fangjianxinxi_cancel:

                click_Cancel();


                break;

            case R.id.ll_fangjian_add:

                click_add_Img();


                break;
            case R.id.ll_fangjian_delete:

                click_Delete_Img();


                break;

        }
    }

    private void updateRoom() {
        mLoadingDialog.show(mContext, "正在修改..");
        String json = JsonMananger.beanToJson(mBiao);

        Map<String, String> map = new HashMap<>();
        map.put("sysId", mRoomId);
        map.put("HouseId", mHouseId);
        map.put("RoomNo", mRoomName);
        map.put("houseLeve", mEt_Louceng.getText().toString());
        map.put("roomNumber", mEt_Woshi.getText().toString());
        map.put("toiletNumber", mEt_Weishengjian.getText().toString());
        map.put("hallNumber", mEt_Ting.getText().toString());
        map.put("rent", String.valueOf(mRent));
        map.put("depositNumber", mEt_Yajinshu.getText().toString());
        map.put("payNumber", mEt_Fuqianshu.getText().toString());
        map.put("json", json);

        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.UPDATE_ROOM, map)
                .execute(new Bean_Callback<BeanResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BeanResponse response, String message) {
                        finish();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });



    }

    private void click_Delete_Img() {

        if (mBiao.size() == 0) {
            ToastUtil.showShort(mContext, "请先添加费用!");
            return;
        }
        if (isDelete) {
            isDelete = false;
            for (int i = 0; i < mBiao.size(); i++) {
                mBiao.get(i).setIsCheck(false);
            }
            mAdapter.notifyDataSetChanged();


            mLl_DeleteLayout.setVisibility(View.GONE);

            mBt_Ok.setVisibility(View.VISIBLE);
            mBt_Lishiheyue.setVisibility(View.VISIBLE);
            return;
        }

        isDelete = true;
        for (int i = 0; i < mBiao.size(); i++) {
            mBiao.get(i).setIsCheck(true);
        }
        mAdapter.notifyDataSetChanged();

        mBt_Ok.setVisibility(View.GONE);
        mBt_Lishiheyue.setVisibility(View.GONE);
        mLl_DeleteLayout.startAnimation(CommonUtil.show_in_bottom());
        mLl_DeleteLayout.setVisibility(View.VISIBLE);

    }

    private void click_add_Img() {
        if (isDelete) {
            ToastUtil.showShort(mContext, "请先取消删除操作!");
            return;
        }

        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < mBiao.size(); i++) {
            strings.add(mBiao.get(i).getCostName());
        }
        Intent intent = new Intent();
        intent.setClass(mContext, House_Add_OtherMoney.class);
        intent.putStringArrayListExtra("name", strings);
        startActivityForResult(intent, Static_Conmom.FANGJIANXINXI_TO_ADD_MONEY);

    }

    private void click_Cancel() {

        isDelete = false;
        for (int i = 0; i < mBiao.size(); i++) {
            mBiao.get(i).setIsCheck(false);
        }
        mAdapter.notifyDataSetChanged();


        mLl_DeleteLayout.setVisibility(View.GONE);

        mBt_Ok.setVisibility(View.VISIBLE);
        mBt_Lishiheyue.setVisibility(View.VISIBLE);

    }

    private void click_Delete_Btn() {
        Dialog_Show.showTwoButton(mContext, "删除提醒", "即将删除选中的条目!", "取消", "确定",
                result -> {
                    if (result.equals("2")) {
                        int size = mBiao.size();
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < mBiao.size(); j++) {

                                if (mBiao.get(j).isNeedDelete()) {
                                    mBiao.remove(j);
                                }
                            }

                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    if (mBiao.size() == 0) {
                        isDelete = false;
                        mLl_DeleteLayout.setVisibility(View.GONE);
                        mBt_Ok.setVisibility(View.VISIBLE);
                        mBt_Lishiheyue.setVisibility(View.VISIBLE);
                    }
                });

    }

    @Override //监控返回按钮
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDelete) {
                for (int i = 0; i < mBiao.size(); i++) {
                    mBiao.get(i).setIsCheck(false);
                }
                mAdapter.notifyDataSetChanged();

                mLl_DeleteLayout.setVisibility(View.GONE);

                mBt_Ok.setVisibility(View.VISIBLE);
                mBt_Lishiheyue.setVisibility(View.VISIBLE);

                isDelete = false;
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Static_Conmom.FANGJIANXINXI_TO_ADD_MONEY) {
            if (resultCode == Static_Conmom.MONEY_RESULT) {
                Bean_House_All_RoomCharge list = (Bean_House_All_RoomCharge) data.getSerializableExtra("list");
                mBiao.add(list);
                mAdapter.notifyDataSetChanged();


            }
        }

    }


}
