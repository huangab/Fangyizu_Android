package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.adapter.Adapter_House_All_Shouzu;
import com.fangku.fyz.modular_house.add_room.House_Add_OtherMoney;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Contract;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.fangku.fyz.widget.Dialog_Show;
import com.flyco.dialog.widget.ActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 收租页面
 * Created by   jie.wang
 * Date: 2016/9/5
 * Time: 0:45
 */
public class House_All_Shouzu extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.tv_house_all_shouzu_addcost)
    TextView mTvAddcost;
    @Bind(R.id.lv_house_all_tuizhu)
    ListView mLvHouseAllTuizhu;
    @Bind(R.id.cb_house_all_shouzu)
    CheckBox mCheckBox;
    @Bind(R.id.tv_house_add_shouzu_total)
    TextView mTvHouseAddShouzuTotal;
    @Bind(R.id.et_house_all_shouzu_money)
    EditText mEtHouseAllShouzuMoney;

    Adapter_House_All_Shouzu mAdapter;
    static List<Bean_House_All_Contract.DataEntity.ZjmxEntity> mList = new ArrayList<>();
    Bean_House_All_RoomCharge roomCharge;
    List<Bean_House_All_RoomCharge> mListRoomCharge = new ArrayList<>();
    ArrayList<String> strings = new ArrayList<>();

    private String landlordId;
    private String roomerId;
    private String houseId;
    private String roomId;
    private String contractId;
    private String state;  //已收/逃单
    private String cost;
    private String startDate;
    private String endDate;
    //房客手机号
    String roomerMobile;

    public static void launch(Context mContext, String contractId, String roomerMobile) {
        Intent mIntent = new Intent(mContext, House_All_Shouzu.class);
        mIntent.putExtra("contractId", contractId);
        mIntent.putExtra("roomerMobile", roomerMobile);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_shouzu;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("收租");
        mImageView.setVisibility(View.GONE);
        mLoadingDialog.show(mContext);
        //注册EventBus
        EventBus.getDefault().register(this);

        Intent intent = getIntent();
        contractId = intent.getStringExtra("contractId");
        roomerMobile = intent.getStringExtra("roomerMobile");

        mAdapter = new Adapter_House_All_Shouzu(House_All_Shouzu.this, mList);
        mLvHouseAllTuizhu.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        //根据合同Id获取房间信息

        Map<String, String> map = new HashMap<>();
        map.put("contractId", contractId);
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.ContractById, map)
                .execute(new Bean_Callback<Bean_House_All_Contract>(mContext) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_All_Contract response, String message) {
                        mList.clear();
                        mList.addAll(response.getData().getZjmx());
                        mAdapter.notifyDataSetChanged();
                        mTvHouseAddShouzuTotal.setText(String.valueOf(response.getData().getZjxx().getCost()));
                        mEtHouseAllShouzuMoney.setText(String.valueOf(response.getData().getZjxx().getCost()));

                        roomerId = response.getData().getHtxx().getRoomerId();
                        roomId = response.getData().getHtxx().getRoomId();
                        houseId = response.getData().getHtxx().getHouseId();
                        landlordId = response.getData().getHtxx().getLandlordId();
                        cost = String.valueOf(response.getData().getZjxx().getCost());
                        startDate = response.getData().getHtxx().getStartDate();
                        endDate = response.getData().getHtxx().getEndDate();

                        for (int i = 0; i < response.getData().getZjmx().size(); i++) {
                            roomCharge = new Bean_House_All_RoomCharge();
                            roomCharge.setContractId(contractId);
                            roomCharge.setRoomId(roomId);
                            roomCharge.setHouseId(houseId);
                            roomCharge.setCostName(response.getData().getZjmx().get(i).getCostName());
                            roomCharge.setCostPrice(response.getData().getZjmx().get(i).getCostPrice());
                            roomCharge.setCostUnit(response.getData().getZjmx().get(i).getCostUnit());
                            roomCharge.setChargeType(response.getData().getZjmx().get(i).getChargeType());
                            mListRoomCharge.add(roomCharge);
                        }

                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        //计算总金额
        Double total = 0.0;
        for (Bean_House_All_Contract.DataEntity.ZjmxEntity rdeEntity : mList) {
            total += rdeEntity.getTotalCost();
        }
        mTvHouseAddShouzuTotal.setText(String.valueOf(total));
        mEtHouseAllShouzuMoney.setText(String.valueOf(total));

        //刷新数据
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_house_all_shouzu_cz, R.id.btn_back, R.id.tv_house_all_shouzu_addcost, R.id.cb_house_all_shouzu, R.id.btn_house_all_shouzu_keep, R.id.btn_house_all_shouzu})
    public void onClick(View view) {
        switch (view.getId()) {
            //催租
            case R.id.btn_house_all_shouzu_cz:
                Uri smsToUri = Uri.parse("smsto:" + roomerMobile);
                Intent mIntent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
                mIntent.putExtra("sms_body", "交租时间到了请尽快交租，你这月要交" + mEtHouseAllShouzuMoney.getText().toString());
                startActivity(mIntent);
                break;
            case R.id.btn_back:
                finish();
                break;
            //增加费用
            case R.id.tv_house_all_shouzu_addcost:
//                House_All_AddCost.launch(mContext);
                for (Bean_House_All_Contract.DataEntity.ZjmxEntity rdeEntity : mList) {
                    if (!rdeEntity.getCostName().equals("押金")) {
                        strings.add(rdeEntity.getCostName());
                    }
                }
                Intent intent = new Intent();
                intent.setClass(mContext, House_Add_OtherMoney.class);
                intent.putStringArrayListExtra("name", strings);
                startActivityForResult(intent, Static_Conmom.FANGJIANXINXI_TO_ADD_MONEY);
                break;
            //是否收费
            case R.id.cb_house_all_shouzu:
                if (mCheckBox.isChecked()) {
                    mLvHouseAllTuizhu.setVisibility(View.VISIBLE);
                } else {
                    mCheckBox.setChecked(true);
                    getBottom();
                }
                break;
            //保存数据
            case R.id.btn_house_all_shouzu_keep:

                Dialog_Show.showTwoButton(mContext, "保存提示", "保存本次费用到服务器?", "取消", "确定", new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        if (result.equals("2")) { //

                        }
                    }
                });

                break;
            //收租
            case R.id.btn_house_all_shouzu:
                addRoomRent();
                break;

        }
    }

    /**
     * 收租
     */
    public void addRoomRent() {
        if (!"".equals(mEtHouseAllShouzuMoney.getText().toString())) {
            mLoadingDialog.show(mContext);
            Map<String, String> map = new HashMap<>();
            map.put("roomerId", roomerId);
            map.put("houseId", houseId);
            map.put("landlordId", landlordId);
            map.put("roomId", roomId);
            map.put("contractId", contractId);
            map.put("state", "已收");
            map.put("cost", mEtHouseAllShouzuMoney.getText().toString());
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("json", JsonMananger.beanToJson(mListRoomCharge));

            PostUtil postUtil = new PostUtil();
            postUtil.Post_Bean(Url_final.ADD_ROOMRENT, map)
                    .execute(new Bean_Callback<BeanResponse>(mContext) {
                        @Override
                        protected void onSuccess_Code200(BeanResponse response, String message) {
                            Fragment_House.isUpdate = true;
                            House_All_Main.isNeedUpdate = true;
                            finish();
                        }

                        @Override
                        protected void onOver() {
                            mLoadingDialog.cancel();
                        }
                    });



        } else {
            ToastUtil.showShort(mContext, "请输入实际金额");
        }
    }

    /**
     * 操作弹出框
     */
    public void getBottom() {
        final String[] stringItems = {"免收", "下次收"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, stringItems, null);
        dialog.title("请选择操作")//
                .titleTextSize_SP(14.5f)//
                .itemHeight(55)
                .show();

        dialog.setOnOperItemClickL((parent, view, position, id) -> {
            switch (position) {
                //免收
                case 0:
                    mLvHouseAllTuizhu.setVisibility(View.GONE);
                    mCheckBox.setChecked(false);
                    dialog.dismiss();
                    break;
                //下次收
                case 1:
                    mLvHouseAllTuizhu.setVisibility(View.GONE);
                    mCheckBox.setChecked(false);
                    dialog.dismiss();
                    break;

            }
            dialog.dismiss();
        });
    }

    /**
     * 接收eventbus传回来的数据
     *
     * @param event 根据这个实体列进行获取
     */
    @Subscribe
    public void onEventMainThread(Bean_House_All_Contract.DataEntity.ZjmxEntity event) {
        mList.add(event);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Static_Conmom.FANGJIANXINXI_TO_ADD_MONEY) {
            if (resultCode == Static_Conmom.MONEY_RESULT) {
                Bean_House_All_RoomCharge list = (Bean_House_All_RoomCharge) data.getSerializableExtra("list");
                Bean_House_All_Contract.DataEntity.ZjmxEntity rde = new Bean_House_All_Contract.DataEntity.ZjmxEntity();
                rde.setCostName(list.getCostName());
                rde.setCostPrice(list.getCostPrice());
                rde.setCostUnit(list.getCostUnit());
                rde.setChargeType(list.getChargeType());
                rde.setTotalCost(list.getCostPrice());
                mList.add(rde);
                mAdapter.notifyDataSetChanged();

            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
        mList.clear();
    }

}
