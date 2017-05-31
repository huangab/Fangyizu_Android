package com.fangku.fyz.modular_house.all_room;/**
 * Created by 67343 on 2016/8/4.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.bean.Bean_Huanfang_RoomCharge;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 换房
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_All_huanfang extends MyBaseActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_huanfang_HouseName)
    TextView mTvHuanfangHouseName;
    @Bind(R.id.tv_huanfang_RoomName)
    TextView mTvHuanfangRoomName;
    @Bind(R.id.tv_huanfang_Data)
    TextView mTvHuanfangData;
    @Bind(R.id.et_huanfang_zhujin)
    EditText mEtHuanfangZhujin;
    @Bind(R.id.et_huanfang_yajin)
    EditText mEtHuanfangYajin;

    private String mHouseID;//当前房产ID
    private String mHouseName;//当前房产名称
    private String mRoomName;//当前房间名称
    private String mRoomId;//当前房间ID
    private Double mRent;//当前房间押金
    private Double mDeposit;//当前房房租
    private String mContractId;//当前合同ID
    private String mRoomerID;//房客ID

    private boolean isnewRoomId = false;

    private String houseId;
    private String roomId;
    Bean_Huanfang_RoomCharge mCharge = new Bean_Huanfang_RoomCharge();

    public static void launch(Context mContext, String HouseID, String houseName, String roomID, String roomerID, String roomNm, Double rent, Double deposit, String contractId) {
        Intent mIntent = new Intent(mContext, House_All_huanfang.class);
        mIntent.putExtra("houseId", HouseID);
        mIntent.putExtra("houseName", houseName);
        mIntent.putExtra("roomId", roomID);
        mIntent.putExtra("roomerId", roomerID);
        mIntent.putExtra("roomNm", roomNm);
        mIntent.putExtra("rent", rent);
        mIntent.putExtra("deposit", deposit);
        mIntent.putExtra("contractId", contractId);
        mContext.startActivity(mIntent);

    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_huanfang;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mLoadingDialog.show(this, "正在准备数据,请稍后....");
        Intent intent = getIntent();
        mHouseID = intent.getStringExtra("houseId");
        mHouseName = intent.getStringExtra("houseName");
        mRoomName = intent.getStringExtra("roomNm");
        mRoomId = intent.getStringExtra("roomId");
        mRoomerID = intent.getStringExtra("roomerId");
        mRent = intent.getDoubleExtra("rent", 0.0);
        mDeposit = intent.getDoubleExtra("deposit", 0.0);
        mContractId = intent.getStringExtra("contractId");

        Map<String, String> map = new HashMap<>();
        map.put("houseId", mHouseID);
        map.put("sysId", mRoomId);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.GET_ROOM_BY_ID, map)
                .execute(new Bean_Callback<Bean_Huanfang_RoomCharge>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_Huanfang_RoomCharge response, String message) {
                        mCharge = response;
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }

                    @Override
                    protected void onFailed(String message) {//当返回code不是200的时候 或者连接成功无数据的时候

                        finish();

                    }
                });

        mTvHuanfangHouseName.setText(mHouseName);
        mTvHuanfangRoomName.setText(mRoomName);
        mEtHuanfangYajin.setText(String.valueOf(mDeposit));
        mEtHuanfangZhujin.setText(String.valueOf(mRent));

        mTvTitle.setText("换房");
    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.ll_select_room, R.id.bt_huanfang_queren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_select_room:
                Intent mIntent = new Intent(mContext, House_All_huanfang_RoomList.class);
                startActivityForResult(mIntent, CONTEXT_IGNORE_SECURITY);
                break;
            case R.id.bt_huanfang_queren:
                if (isnewRoomId) {
                    dohuanfang();
                } else {
                    ToastUtil.showShort(mContext, "请选择目标房间！");
                }
                break;
        }
    }

    private void dohuanfang() {
        mLoadingDialog.show(this);
        Map<String, String> map = new HashMap<>();
        map.put("houseId", houseId);
        map.put("roomId", mRoomId);
        map.put("newRoomId", roomId);
        map.put("sysId", mContractId);
        map.put("rent", mEtHuanfangZhujin.getText().toString());
        map.put("deposit", mEtHuanfangYajin.getText().toString());
        map.put("startDate", "2016-9-29");
        map.put("roomerId", mRoomerID);
        map.put("endDate", "2016-9-26");
        map.put("rentCycle", "30");
        map.put("chargeCycle", "30");
        map.put("json", JsonMananger.beanToJson(mCharge.getData().getFy()));


        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.SaveChageRoomContract, map)
                .execute(new Bean_Callback<BaseResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BaseResponse response, String message) {
                        House_All_Main.isNeedUpdate = true;
                        Fragment_House.isUpdate = true;
                        finish();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTEXT_IGNORE_SECURITY) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                mTvHuanfangData.setText(bundle.getString("data"));
                houseId = bundle.getString("houseId");
                roomId = bundle.getString("roomId");
                isnewRoomId = true;
            }
        }
    }
}
