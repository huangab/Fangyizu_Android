package com.fangku.fyz.modular_house.all_room;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.base.ActivityManager;
import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.common.UserEntity;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.widget.AllListView;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Static_Conmom;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.adapter.Adapter_Money_list;
import com.fangku.fyz.modular_house.add_room.House_Add_OtherMoney;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Contract;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Heyue2;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Roomer;
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
 * 出租2
 * Created by   jie.wang
 * Date: 2016/8/23
 * Time: 17:30
 */
public class House_All_chuzu2 extends MyBaseActivity {

    @Bind(R.id.bt_heyue2_delete)
    Button mBtHeyue2Delete;
    @Bind(R.id.bt_heyue2_cancel)
    Button mBtHeyue2Cancel;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_heyue_qizuriqi)
    TextView mTvHeyueQizuriqi;
    @Bind(R.id.ll_heyue_qizuriqi)
    LinearLayout mLlHeyueQizuriqi;
    @Bind(R.id.et_heyue_zuqi)
    EditText mEtHeyueZuqi;
    @Bind(R.id.tv_heyue_jiesuriqi)
    TextView mTvHeyueJiesuriqi;
    @Bind(R.id.ll_heyue_jiesuriqi)
    LinearLayout mLlHeyueJiesuriqi;
    @Bind(R.id.lv_qitfeiyong)
    AllListView mLvQitfeiyong;
    @Bind(R.id.et_heyue_zujin)
    EditText mEtHeyueZujin;
    @Bind(R.id.et_heyue_qitafeiyong)
    EditText mEtHeyueQitafeiyong;
    @Bind(R.id.et_heyue_chaobiaotixing)
    EditText mEtHeyueChaobiaotixing;
    @Bind(R.id.sc_heyue)
    ScrollView mScHeyue;

    List<Bean_House_All_RoomCharge> mBiao = new ArrayList<>();

    Bean_House_All_Roomer.DataEntity roomer;

    private Adapter_Money_list mAdapter;

    private boolean isDelete;

    //设置年月日
    int mYear = 2016, mMonth = 9, mDay = 1;

    Bean_House_All_Contract contract = new Bean_House_All_Contract();

    public static void launch(Context mContext, String rent, String deposit, Bean_House_All_Roomer.DataEntity roomer, String roomId, String houseId, String landlordCardId) {
        Intent mIntent = new Intent(mContext, House_All_chuzu2.class);
        mIntent.putExtra("rent", rent);//正面照片
        mIntent.putExtra("deposit", deposit);//背面照片
        mIntent.putExtra("roomer", roomer);
        mIntent.putExtra("roomId", roomId);
        mIntent.putExtra("houseId", houseId);
        mIntent.putExtra("landlordCardId", landlordCardId);
        mContext.startActivity(mIntent);
    }


    @Override
    public int bindLayout() {
        return R.layout.house_all_heyue2;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mScHeyue.smoothScrollTo(0, 0);

        List();

        mTvTitle.setText("出租");

        initEdlistener();

        //关于日期的设置开始
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        mTvHeyueQizuriqi.setText(df.format(new Date()));
        mEtHeyueZuqi.setText("3");

        mTvHeyueJiesuriqi.setText(CommonUtil.setTime(mEtHeyueZuqi.getText().toString(), mTvHeyueQizuriqi.getText().toString()));

        //关于日期的设置结束

        mAdapter = new Adapter_Money_list(House_All_chuzu2.this, mBiao);
        mLvQitfeiyong.setAdapter(mAdapter);

    }

    /**
     * 初始化列表数据
     */
    private void List() {
        Bean_House_All_RoomCharge houseCharge1 = new Bean_House_All_RoomCharge();
        Bean_House_All_RoomCharge houseCharge2 = new Bean_House_All_RoomCharge();
        Bean_House_All_RoomCharge houseCharge3 = new Bean_House_All_RoomCharge();

        houseCharge1.setCostName("水表");
        houseCharge1.setCostPrice((double) 5);
        houseCharge1.setCostUnit("元/方");
        houseCharge1.setMinValue(0);
        houseCharge1.setChargeType("2");
        houseCharge1.setOperate("A");

        houseCharge2.setCostName("电表");
        houseCharge2.setCostPrice(1.5);
        houseCharge2.setCostUnit("元/度");
        houseCharge2.setMinValue(0);
        houseCharge2.setChargeType("2");
        houseCharge2.setOperate("A");

        houseCharge3.setCostName("管理费");
        houseCharge3.setCostPrice((double) 0);
        houseCharge3.setCostUnit("元/月");
        houseCharge3.setMinValue(0);
        houseCharge3.setChargeType("1");
        houseCharge3.setOperate("A");


        mBiao.add(houseCharge1);
        mBiao.add(houseCharge2);
        mBiao.add(houseCharge3);


    }


    @Override
    public void getData() {

    }


    @OnClick({R.id.btn_back, R.id.bt_heyue2_next, R.id.bt_heyue2_cancel, R.id.ll_heyue_delete,
            R.id.bt_heyue2_delete, R.id.ll_heyue_add, R.id.ll_heyue_qizuriqi, R.id.ll_heyue_jiesuriqi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_heyue2_cancel:

                click_Cancel();
                break;
            case R.id.bt_heyue2_delete:

                click_Delete_Btn();
                break;
            case R.id.ll_heyue_add:

                click_add_Img();

                break;
            case R.id.ll_heyue_delete:

                click_Delete_Img();

                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_heyue2_next:
                mLoadingDialog.show(mContext);
                save();
                break;

            case R.id.ll_heyue_qizuriqi:
                time(mTvHeyueQizuriqi);
                break;
            case R.id.ll_heyue_jiesuriqi:
//                time(mTvHeyueJiesuriqi);
                break;
        }
    }


    public void save() {
        //获取合约1传过来的租客信息并加入到list中进行传值
        Intent mIntent = getIntent();
        roomer = (Bean_House_All_Roomer.DataEntity) mIntent.getSerializableExtra("roomer");
        List<Bean_House_All_Roomer.DataEntity> mList = new ArrayList<>();
        mList.add(roomer);
        //给抄表数据添加日期
        int size = House_All_chuzu.mBiao.size();
        for (int i = 0; i < size; i++) {
            House_All_chuzu.mBiao.get(i).setThisDate(mTvHeyueQizuriqi.getText().toString());
        }
        //获取房东信息
        UserEntity userEntity = UserDataUtil.getUserInfo();
        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> map = new HashMap<>();
        map.put("houseId", mIntent.getStringExtra("houseId"));
        map.put("roomerId", roomer.getSysId());
        map.put("roomId", mIntent.getStringExtra("roomId"));
        map.put("rent", mIntent.getStringExtra("rent"));
        map.put("deposit", mIntent.getStringExtra("deposit"));
        map.put("startDate", mTvHeyueQizuriqi.getText().toString());
        map.put("endDate", mTvHeyueJiesuriqi.getText().toString());
        map.put("rentCycle", mEtHeyueZujin.getText().toString());
        map.put("chargeCycle", mEtHeyueQitafeiyong.getText().toString());
        map.put("warmDay", mEtHeyueChaobiaotixing.getText().toString());
        map.put("content", "合约");
        map.put("roomerCardId", roomer.getIdCardNo());
        map.put("roomerName", roomer.getRealName());
        map.put("landlordCardId", mIntent.getStringExtra("landlordCardId"));
        map.put("landlordName", userEntity.getData().getName());
        map.put("landlordMobile", userEntity.getData().getUsername());
        map.put("roomerMobile", roomer.getRoomerMoblie());
        map.put("contractType", "新签");
        map.put("roomCharge", JsonMananger.beanToJson(mBiao));
        map.put("roomer", JsonMananger.beanToJson(mList));
        map.put("meter", JsonMananger.beanToJson(House_All_chuzu.mBiao));

        //保存合约信息
        httpUtil.doPost(Url_final.addContract, map, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("result", "onSuccess: " + result);
                Bean_House_All_Heyue2 baseResponse = JsonMananger.jsonToBean(result, Bean_House_All_Heyue2.class);
                if (baseResponse != null) {
                    if ("200".equals(baseResponse.getCode())) {
                        updateRoomer(baseResponse.getData().getCID());
                    } else {

                        ToastUtil.showShort(mContext, baseResponse.getMessage());
                    }
                } else {

                    ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
                }
                mLoadingDialog.cancel();
            }

            @Override
            public void onFailed(Exception e) {
                mLoadingDialog.cancel();
                ToastUtil.showShort(mContext, R.string.will_dopost_onFailed);
            }
        });


    }

    /**
     * 修改房客信息（这里写是为了解决没有验证，房客拿不到合同id的问题）
     */
    public void updateRoomer(String contractId) {
        HttpUtil httpUtil = new HttpUtil();
        Map<String, String> map = new HashMap<>();
        map.put("landlordId", roomer.getLandlordId());
        map.put("houseId", roomer.getHouseId());
        map.put("roomId", roomer.getRoomId());
        map.put("contractId", contractId);
        map.put("sysId", roomer.getSysId());
        map.put("workPlace", roomer.getWorkPlace());
        map.put("idCardNo", roomer.getIdCardNo());
        map.put("realName", roomer.getRealName());
        map.put("roomerMoblie", roomer.getRoomerMoblie());
        map.put("gender", roomer.getGender());
        map.put("isCheck", "n");
        map.put("isMaster", "y");
        httpUtil.doPost(Url_final.UPDATE_ROOMER, map, new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mLoadingDialog.cancel();
                BaseResponse baseResponse = JsonMananger.jsonToBean(result, BaseResponse.class);
                if (baseResponse != null) {
                    if ("200".equals(baseResponse.getCode())) {
                        ToastUtil.showShort(mContext, "出租成功");
                        House_All_Main.isNeedUpdate = true;
                        Fragment_House.isUpdate = true;
                        ActivityManager.getInstance().removeActivity(House_All_chuzu2.class);
                        ActivityManager.getInstance().removeActivity(House_All_chuzu.class);


                    } else {
                        ToastUtil.showShort(mContext, baseResponse.getMessage());
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


    //点击了取消按钮
    private void click_Cancel() {
        isDelete = false;
        for (int i = 0; i < mBiao.size(); i++) {
            mBiao.get(i).setIsCheck(false);
        }
        mAdapter.notifyDataSetChanged();
        mBtHeyue2Cancel.setVisibility(View.GONE);
        mBtHeyue2Delete.setVisibility(View.GONE);
        findViewById(R.id.bt_heyue2_next).setVisibility(View.VISIBLE);

    }

    //点击了删除按钮
    private void click_Delete_Btn() {
        Dialog_Show.showTwoButton(mContext, "删除提醒", "即将删除选中的条目!", "取消", "确定", new Only_CallBack() {
            @Override
            public void isSuccess(String result) {
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
                    mBtHeyue2Cancel.setVisibility(View.GONE);
                    mBtHeyue2Delete.setVisibility(View.GONE);
                    findViewById(R.id.bt_heyue2_next).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //点击了删除图片
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
            mBtHeyue2Cancel.setVisibility(View.GONE);
            mBtHeyue2Delete.setVisibility(View.GONE);
            findViewById(R.id.bt_heyue2_next).setVisibility(View.VISIBLE);
            return;
        }

        isDelete = true;
        for (int i = 0; i < mBiao.size(); i++) {
            mBiao.get(i).setIsCheck(true);
        }
        mAdapter.notifyDataSetChanged();
        findViewById(R.id.bt_heyue2_next).setVisibility(View.GONE);
        mBtHeyue2Cancel.startAnimation(CommonUtil.show_in_bottom());
        mBtHeyue2Delete.startAnimation(CommonUtil.show_in_bottom());
        mBtHeyue2Cancel.setVisibility(View.VISIBLE);
        mBtHeyue2Delete.setVisibility(View.VISIBLE);


    }

    /**
     * 点击了添加按钮
     */
    public void click_add_Img() {
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
        startActivityForResult(intent, Static_Conmom.HEYUE_TO_ADD_MONEY);

    }

    //监控文字输入状态
    private void initEdlistener() {
        mEtHeyueZuqi.addTextChangedListener(
                new TextWatcher() {

                    //退格时执行此方法,长度为0时不执行   ,输入时也执行
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
                    }

                    //输入时时执行此方法,长度为0时不执行
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Log.i("fffffff", "on");
                        String a = s.toString().trim();
                        if ("".equals(a)) {
                            return;
                        }
                        mTvHeyueJiesuriqi.setText(CommonUtil.setTime(a, mTvHeyueQizuriqi.getText().toString()));


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.i("fffffff", "after");
                        if (s.length() == 0) {
                            mTvHeyueJiesuriqi.setText(mTvHeyueQizuriqi.getText().toString());
                        }
                    }
                }

        );
    }


    /**
     * 日期弹出框
     */
    public void time(final TextView textView) {
        //创建DatePickerDialog对象
        DatePickerDialog dpd = new DatePickerDialog(mContext, (view, year, monthOfYear, dayOfMonth) -> {
            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            //更新日期

            String mDate = mYear + "-" + (mMonth < 9 ? "0" + String.valueOf(mMonth + 1) : (mMonth + 1)) + "-" + mDay;
            textView.setText(mDate);
            if (textView.getId() == R.id.tv_heyue_qizuriqi) {
                mTvHeyueJiesuriqi.setText(CommonUtil.setTime(mEtHeyueZuqi.getText().toString(), mTvHeyueQizuriqi.getText().toString()));

            }
        }, mYear, mMonth, mDay);
        dpd.show();//显示DatePickerDialog组件
    }


    @Override //实现后台功能
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDelete) {
                for (int i = 0; i < mBiao.size(); i++) {
                    mBiao.get(i).setIsCheck(false);
                }
                mAdapter.notifyDataSetChanged();
                mBtHeyue2Cancel.setVisibility(View.GONE);
                mBtHeyue2Delete.setVisibility(View.GONE);
                findViewById(R.id.bt_heyue2_next).setVisibility(View.VISIBLE);
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
        if (requestCode == Static_Conmom.HEYUE_TO_ADD_MONEY) {
            if (resultCode == Static_Conmom.MONEY_RESULT) {
                Bean_House_All_RoomCharge list = (Bean_House_All_RoomCharge) data.getSerializableExtra("list");
                mBiao.add(list);
                mAdapter.notifyDataSetChanged();


            }
        }

    }


}
