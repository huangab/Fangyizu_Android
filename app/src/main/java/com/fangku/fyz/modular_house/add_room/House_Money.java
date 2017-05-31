package com.fangku.fyz.modular_house.add_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_Money_list;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.modular_house.bean.Bean_House_RoomData;
import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;
import com.fangku.fyz.sql.Sql_Table_HouseMoney;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.fangku.fyz.widget.Dialog_Show;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 房源费用
 * Created by jie.wang
 * Date: 2016/8/30
 * Time: 14:36
 */
public class House_Money extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.lv_house_money_lis)
    ListView mLvHouseMoneyLis;

    @Bind(R.id.bt_house_money_next)
    Button mBtHouseMoneyNext;
    @Bind(R.id.ib_AddMoney)
    ImageButton mIbAddMoney;
    @Bind(R.id.ib_ClearMoney)
    ImageButton mIbClearMoney;
    @Bind(R.id.bt_money_delete)
    Button mBtMoneyDelete;
    @Bind(R.id.bt_money_cancel)
    Button mBtMoneyCancel;
    @Bind(R.id.ll_money_father)
    LinearLayout mLlMoneyFather;

    static boolean isDispersed = false;

    private List<Bean_House_All_RoomCharge> houseCharge = new ArrayList<>();

    private Adapter_Money_list mAdapter;
    public static House_Money money;

    Sql_Table_HouseMoney tableHouseMoney;

    boolean isDelete = false;

    public static void launch(Context mContext, boolean i) {
        Intent mIntent = new Intent(mContext, House_Money.class);
        mContext.startActivity(mIntent);
        isDispersed = i;
    }

    @Override
    public int bindLayout() {
        return R.layout.house_money;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        money = this;
        mTvTitle.setText("费用设置");
        mAdapter = new Adapter_Money_list(this, houseCharge);
        mLvHouseMoneyLis.setAdapter(mAdapter);

        tableHouseMoney = new Sql_Table_HouseMoney(this);

        List<Bean_House_All_RoomCharge> houseChargedata = tableHouseMoney.select();
        if (houseChargedata.size() > 0) {
            houseCharge.addAll(houseChargedata);
        } else {
            initListView();
        }

        if (isDispersed) {
            mBtHouseMoneyNext.setText("新建");
        }

    }

    @Override
    public void getData() {

    }


    /**
     * 初始化列表数据
     */
    private void initListView() {

        Bean_House_All_RoomCharge houseCharge1 = new Bean_House_All_RoomCharge();
        Bean_House_All_RoomCharge houseCharge2 = new Bean_House_All_RoomCharge();
        Bean_House_All_RoomCharge houseCharge3 = new Bean_House_All_RoomCharge();


        houseCharge1.setCostName("水表");
        houseCharge1.setCostPrice((double) 5);
        houseCharge1.setCostUnit("元/方");
        houseCharge1.setMinValue(0);
        houseCharge1.setChargeType("2");
        houseCharge1.setOperate("A");
        houseCharge1.setMeterCode("E");


        houseCharge2.setCostName("电表");
        houseCharge2.setCostPrice(1.5);
        houseCharge2.setCostUnit("元/度");
        houseCharge2.setMinValue(0);
        houseCharge2.setChargeType("2");
        houseCharge2.setOperate("A");
        houseCharge2.setMeterCode("W");

        houseCharge3.setCostName("管理费");
        houseCharge3.setCostPrice((double) 0);
        houseCharge3.setCostUnit("元/月");
        houseCharge3.setMinValue(0);
        houseCharge3.setChargeType("1");


        houseCharge.add(houseCharge1);
        houseCharge.add(houseCharge2);
        houseCharge.add(houseCharge3);


    }


    @OnClick({R.id.btn_back, R.id.bt_house_money_next, R.id.ib_AddMoney, R.id.ib_ClearMoney, R.id.bt_money_delete, R.id.bt_money_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_house_money_next:

                if (!isDispersed) {//判断是否为集中式房产
                    tableHouseMoney.clear();
                    tableHouseMoney.insert(houseCharge);
                    House_Add_Focus.mHuoseCharge.addAll(houseCharge);
                    House_Add_Focus_Select.launch(mContext);
                } else {
                    mLoadingDialog.show(mContext, "正在新建分散房产.....");
                    createNewDispersed();
                }
                break;

            case R.id.ib_AddMoney:
                click_add_Img();
                break;

            case R.id.ib_ClearMoney:

                click_Delete_Img();


                break;

            case R.id.bt_money_delete:
                click_Delete_Btn();


                break;

            case R.id.bt_money_cancel:

                click_Cancel();


                break;
        }
    }

    private void click_Delete_Img() {
        if (houseCharge.size() == 0) {
            ToastUtil.showShort(mContext, "请先添加费用!");
            return;
        }

        if (isDelete) {
            isDelete = false;
            for (int i = 0; i < houseCharge.size(); i++) {
                houseCharge.get(i).setIsCheck(false);
            }
            mAdapter.notifyDataSetChanged();
            mBtMoneyCancel.setVisibility(View.GONE);
            mBtMoneyDelete.setVisibility(View.GONE);

            mBtHouseMoneyNext.startAnimation(CommonUtil.show_in_bottom());
            mLlMoneyFather.startAnimation(CommonUtil.show_in_bottom());
            mBtHouseMoneyNext.setVisibility(View.VISIBLE);
            mLlMoneyFather.setVisibility(View.VISIBLE);


            return;
        }

        isDelete = true;
        for (int i = 0; i < houseCharge.size(); i++) {
            houseCharge.get(i).setIsCheck(true);
        }
        mAdapter.notifyDataSetChanged();
        mBtMoneyCancel.setVisibility(View.VISIBLE);
        mBtMoneyDelete.setVisibility(View.VISIBLE);
        mBtHouseMoneyNext.setVisibility(View.GONE);
        mLlMoneyFather.setVisibility(View.GONE);
    }

    private void click_add_Img() {

        if (isDelete) {
            ToastUtil.showShort(mContext, "请先取消删除操作!");
            return;
        }

        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < houseCharge.size(); i++) {
            strings.add(houseCharge.get(i).getCostName());
        }
        Intent intent = new Intent();
        intent.setClass(mContext, House_Add_OtherMoney.class);
        intent.putStringArrayListExtra("name", strings);
        startActivityForResult(intent, Static_Conmom.MONEY_TO_ADD_MONEY);
    }

    private void click_Delete_Btn() {

        Dialog_Show.showTwoButton(mContext, "删除提醒", "即将删除选中的条目!", "取消", "确定", new Only_CallBack() {
            @Override
            public void isSuccess(String result) {
                if (result.equals("2")) {
                    int size = houseCharge.size();
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < houseCharge.size(); j++) {

                            if (houseCharge.get(j).isNeedDelete()) {
                                houseCharge.remove(j);
                            }
                        }

                    }
                    mAdapter.notifyDataSetChanged();
                }
                if (houseCharge.size() == 0) {
                    isDelete = false;
                    mBtMoneyCancel.setVisibility(View.GONE);
                    mBtMoneyDelete.setVisibility(View.GONE);
                    mBtHouseMoneyNext.setVisibility(View.VISIBLE);
                    mLlMoneyFather.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    private void click_Cancel() {
        isDelete = false;
        for (int i = 0; i < houseCharge.size(); i++) {
            houseCharge.get(i).setIsCheck(false);
        }
        mAdapter.notifyDataSetChanged();
        mBtMoneyCancel.setVisibility(View.GONE);
        mBtMoneyDelete.setVisibility(View.GONE);
        mBtHouseMoneyNext.setVisibility(View.VISIBLE);
        mLlMoneyFather.setVisibility(View.VISIBLE);


    }

    private void createNewDispersed() {
        Bean_Huose_Add addData = House_Add_Dispersed.houseAdd;
        List<Bean_House_RoomData> roomDatas = new ArrayList<>();
        Map<String, String> house = new HashMap<>();
        house.put("addrDetail", addData.getAddrDetali());
        house.put("region", addData.getRegion());
        house.put("houseName", addData.getHouseName());
        house.put("houseLevel", addData.getHouseLevel());
        house.put("roomNumber", addData.getRoomNumber() + "");
        house.put("lng", addData.getLng().toString());
        house.put("lat", addData.getLat().toString());
        house.put("houseType", addData.getHouseType());
        house.put("rentType", addData.getRentType() + "");
        house.put("isLift", addData.getIsLift());
        house.put("emptyNumber", addData.getRoomNumber() + "");
        house.put("jhChargeList", JsonMananger.beanToJson(houseCharge));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        for (int i = 0; i < Integer.valueOf(addData.getRoomNumber()); i++) {
            Bean_House_RoomData mRoom = new Bean_House_RoomData();
            String num = "";
            if (i < 10) {
                num = "00" + i;
            } else if (i >= 10) {
                num = "0" + i;
            }
            mRoom.setRoomNo(String.valueOf(num));
            mRoom.setHouseLeve(String.valueOf(0));
            mRoom.setState("未出租");
            mRoom.setRoomNumber("1");
            mRoom.setToiletNumber("1");
            mRoom.setHallNumber("1");
            mRoom.setCheckDate(str);
            mRoom.setDepositNumber("1");
            mRoom.setPayNumber("1");
            roomDatas.add(mRoom);
        }
        house.put("jroomList", JsonMananger.beanToJson(roomDatas));

        final String finalHouseData = "房产名：" + addData.getHouseName() + "\n楼层数" + addData.getHouseLevel() + "\n总房间数：" + addData.getRoomNumber() + "\n地址：" + addData.getRegion() + addData.getAddrDetali();

        PostUtil postUtil=new PostUtil();
        postUtil.Post_Bean(Url_final.ADD_HOUSE2, house).execute(new Bean_Callback<BaseResponse>(mContext) {
            @Override
            protected void onSuccess_Code200(BaseResponse response, String message) {
                Dialog_Add_HouseSuccess addHouseSuccess = new Dialog_Add_HouseSuccess();
                addHouseSuccess.show(House_Money.this, finalHouseData);
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
        if (requestCode == Static_Conmom.MONEY_TO_ADD_MONEY) {
            if (resultCode == Static_Conmom.MONEY_RESULT) {
                Bean_House_All_RoomCharge list = (Bean_House_All_RoomCharge) data.getSerializableExtra("list");
                houseCharge.add(list);
                mAdapter.notifyDataSetChanged();
                Log.i("返回的值", "onActivityResult: " + houseCharge.toString());


            }
        }

    }


    @Override //实现后台功能
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDelete) {
                for (int i = 0; i < houseCharge.size(); i++) {
                    houseCharge.get(i).setIsCheck(false);
                }
                mAdapter.notifyDataSetChanged();
                mBtMoneyCancel.setVisibility(View.GONE);
                mBtMoneyDelete.setVisibility(View.GONE);
                mBtHouseMoneyNext.setVisibility(View.VISIBLE);
                mLlMoneyFather.setVisibility(View.VISIBLE);
                isDelete = false;

            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
