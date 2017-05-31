package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Roomer;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 租客 详情
 * Created by   jie.wang
 * Date: 2016/8/8
 * Time: 10:39
 */
public class House_All_zuke_message extends MyBaseActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_house_all_zuke_message_zhengmian)
    ImageView mIvHouseAllZukeMessageZhengmian;
    @Bind(R.id.iv_house_all_zuke_message_fangmian)
    ImageView mIvHouseAllZukeMessageFangmian;
    @Bind(R.id.et_house_all_zuke_message_realname)
    EditText mEtHouseAllZukeMessageRealname;
    @Bind(R.id.et_house_all_zuke_message_num)
    EditText mEtHouseAllZukeMessageNum;
    @Bind(R.id.et_house_all_zuke_message_phone)
    EditText mEtHouseAllZukeMessagePhone;
    @Bind(R.id.et_house_all_zuke_message_map)
    EditText mEtHouseAllZukeMessageMap;
    @Bind(R.id.rb_house_all_zuke_message_man)
    RadioButton mRbHouseAllZukeMessageMan;
    @Bind(R.id.rb_house_all_zuke_message_woman)
    RadioButton mRbHouseAllZukeMessageWoman;
    @Bind(R.id.bt_house_all_zuke_message_ok)
    Button mBtHouseAllZukeMessageOk;

    boolean tag;

    String roomId;
    String houseId;
    String landlordId;
    String contractId;
    String isMaster;
    String sysId;

    public static void launch(Context mContext, boolean tag, String sysId, String name, String moblie, String idCardNo, String map, String sex, String roomId, String houseId, String contractId, String landlordId, String isMaster) {
        Intent mIntent = new Intent(mContext, House_All_zuke_message.class);
        mIntent.putExtra("tag", tag);
        mIntent.putExtra("sysId", sysId);
        mIntent.putExtra("name", name);
        mIntent.putExtra("moblie", moblie);
        mIntent.putExtra("idCardNo", idCardNo);
        mIntent.putExtra("map", map);
        mIntent.putExtra("sex", sex);
        mIntent.putExtra("roomId", roomId);
        mIntent.putExtra("houseId", houseId);
        mIntent.putExtra("isMaster", isMaster);
        mIntent.putExtra("landlordId", landlordId);
        mIntent.putExtra("contractId", contractId);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_zuke_message;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        tag = mIntent.getBooleanExtra("tag", true);
        sysId = mIntent.getStringExtra("sysId");
        roomId = mIntent.getStringExtra("roomId");
        houseId = mIntent.getStringExtra("houseId");
        landlordId = mIntent.getStringExtra("landlordId");
        contractId = mIntent.getStringExtra("contractId");
        isMaster = mIntent.getStringExtra("isMaster");

        if (tag) {
            mTvTitle.setText("修改租客信息");
            mBtHouseAllZukeMessageOk.setText("修改");
            mEtHouseAllZukeMessageRealname.setText(mIntent.getStringExtra("name"));
            mEtHouseAllZukeMessageNum.setText(mIntent.getStringExtra("idCardNo"));
            mEtHouseAllZukeMessagePhone.setText(mIntent.getStringExtra("moblie"));
            if (!mIntent.getStringExtra("map").equals("null")) {
                mEtHouseAllZukeMessageMap.setText(mIntent.getStringExtra("map"));
            }
            if ("男".equals(mIntent.getStringExtra("sex"))) {
                mRbHouseAllZukeMessageMan.setChecked(true);
            } else {
                mRbHouseAllZukeMessageWoman.setChecked(true);
            }
        } else {
            mTvTitle.setText("新增租客信息");
            mBtHouseAllZukeMessageOk.setText("保存");
        }
    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.ll_house_all_zuke_message_saomiao, R.id.iv_house_all_zuke_message_zhengmian, R.id.iv_house_all_zuke_message_fangmian, R.id.bt_house_all_zuke_message_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_house_all_zuke_message_saomiao:
                break;
            case R.id.iv_house_all_zuke_message_zhengmian:
                break;
            case R.id.iv_house_all_zuke_message_fangmian:
                break;
            case R.id.bt_house_all_zuke_message_ok:
                addRoomer();
                break;
        }
    }

    /**
     * 新增租客或修改租客信息
     */
    public void addRoomer() {
        String Url;
        String sex;
        if (tag) {
            Url = Url_final.UPDATE_ROOMER;
        } else {
            Url = Url_final.ADD_ROOMER;
        }
        if (mRbHouseAllZukeMessageMan.isChecked()) {
            sex = "男";
        } else {
            sex = "女";
        }
        if (!"".equals(mEtHouseAllZukeMessageRealname.getText().toString()) && !"".equals(mEtHouseAllZukeMessageNum.getText().toString()) && !"".equals(mEtHouseAllZukeMessagePhone.getText().toString())) {
            HttpUtil httpUtil = new HttpUtil();
            Map<String, String> map = new HashMap<>();
            map.put("landlordId", landlordId);
            if (tag) {
                map.put("sysId", sysId);
            }
            map.put("houseId", houseId);
            map.put("roomId", roomId);
            map.put("contractId", contractId);
            if (isMaster != null) {
                map.put("isMaster", isMaster);
            } else {
                map.put("isMaster", "n");
            }
            map.put("idCardNo", mEtHouseAllZukeMessageNum.getText().toString());
            map.put("realName", mEtHouseAllZukeMessageRealname.getText().toString());
            map.put("roomerMoblie", mEtHouseAllZukeMessagePhone.getText().toString());
            if (!"".equals(mEtHouseAllZukeMessageMap.getText().toString().trim())) {
                map.put("workPlace", mEtHouseAllZukeMessageMap.getText().toString());
            }
            map.put("gender", sex);
            map.put("isCheck", "n");
            httpUtil.doPost(Url, map, new JsonCallBack() {
                @Override
                public void onSuccess(String result) {
                    Bean_House_All_Roomer bean_house_all_roomer = JsonMananger.jsonToBean(result, Bean_House_All_Roomer.class);
                    if (bean_house_all_roomer != null) {
                        if ("200".equals(bean_house_all_roomer.getCode())) {
                            ToastUtil.showShort(mContext, bean_house_all_roomer.getMessage());
                            finish();
                        } else {
                            ToastUtil.showShort(mContext, bean_house_all_roomer.getMessage());
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
        } else {
            ToastUtil.showShort(mContext, "请输入完整租客信息");
        }

    }
}
