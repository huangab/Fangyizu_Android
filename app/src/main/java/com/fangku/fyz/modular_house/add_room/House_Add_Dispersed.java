package com.fangku.fyz.modular_house.add_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_GridView_DataZhouBian;
import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加分散式房源
 * Created by bowen.ye
 * Date: 2016/7/18
 * Time: 16:08
 */
public class House_Add_Dispersed extends MyBaseActivity implements House_Add_Dialog.OnItemData {


    Context mContext = House_Add_Dispersed.this;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rb_button_left)
    RadioButton mRbButtonLeft;
    @Bind(R.id.rb_button_right)
    RadioButton mRbButtonRight;
    @Bind(R.id.iv_dispersed_shanjiao)
    ImageView mIvDispersedShanjiao;
    @Bind(R.id.ll_dispersed_ZuoBianPeiTao)
    RelativeLayout mLlDispersedZuoBianPeiTao;
    @Bind(R.id.nsgv_dispersed_zoubian)
    NoScrollGridView mNsgvDispersedZoubian;

    //周边相关
    Adapter_GridView_DataZhouBian dataListAdapter;
    List<Bean_ListData> dataList = new ArrayList<>();
    List<Bean_ListData> data;

    static Bean_Huose_Add houseAdd = new Bean_Huose_Add();
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.et_dispersed_nameHome)
    EditText mEtDispersedNameHome;
    @Bind(R.id.tv_dispersed_rooms_num)
    TextView mTvDispersedRoomsNum;
    @Bind(R.id.bt_dispersed_next)
    Button mBtDispersedNext;
    @Bind(R.id.tv_address_message)
    TextView mTvAddressMessage;
    //数量选择
    House_Add_Dialog dialog;
    @Bind(R.id.pb_dispersed)
    ProgressBar mPbDispersed;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, House_Add_Dispersed.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_add_dispersed;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("新增分散式房产");

        dataListAdapter = new Adapter_GridView_DataZhouBian(this, dataList);
        mNsgvDispersedZoubian.setAdapter(dataListAdapter);
        houseAdd.setHouseType("分散");
        houseAdd.setRentType("合租");
    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.tv_title, R.id.btn_back, R.id.bt_dispersed_next, R.id.ll_dispersed_address, R.id.ll_dispersed_set_room_num, R.id.rb_button_left, R.id.rb_button_right, R.id.ll_dispersed_ZuoBianPeiTao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_dispersed_next:
                next();
                break;
            case R.id.rb_button_left://整租 合租切换


            case R.id.rb_button_right:
                upCheckBox();
                break;
            case R.id.ll_dispersed_ZuoBianPeiTao:
                if (mNsgvDispersedZoubian.getVisibility() != View.VISIBLE) {
                    mNsgvDispersedZoubian.setVisibility(View.VISIBLE);
                    mIvDispersedShanjiao.setImageResource(R.mipmap.xiala_gray_icon_fang);
                    if (data == null) {
                        addZhoubianData();
                        mPbDispersed.setVisibility(View.VISIBLE);
                    }
                } else {
                    mNsgvDispersedZoubian.setVisibility(View.GONE);
                    mIvDispersedShanjiao.setImageResource(R.mipmap.xiala_gray_icon);
                }
                break;
            case R.id.ll_dispersed_address:
                Intent intent = new Intent(House_Add_Dispersed.this, House_Get_Address.class);
                this.startActivityForResult(intent, CONTEXT_INCLUDE_CODE);
                break;
            case R.id.ll_dispersed_set_room_num:
                dialog = new House_Add_Dialog();
                dialog.SetOnItemListent(this);
                dialog.show(this, 2);
                break;
        }
    }

    private void next() {

        if (TextUtils.isEmpty(houseAdd.getAddrDetali())) {
            ToastUtil.showShort(getApplicationContext(), "请输入地址！");
            return;
        }
        if (TextUtils.isEmpty(houseAdd.getRoomNumber())) {
            ToastUtil.showShort(getApplicationContext(), "请选择房间数量！");
            return;
        }
        if (TextUtils.isEmpty(mEtDispersedNameHome.getText().toString().trim())) {
            ToastUtil.showShort(getApplicationContext(), "请输入房源名称！");
            return;
        } else {
            houseAdd.setHouseName(mEtDispersedNameHome.getText().toString().trim());
        }

        House_Money.launch(mContext, true);
    }

    private void addZhoubianData() {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doPost(Url_final.GET_BASELIST + "AroundSupport/" + "false", new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mPbDispersed.setVisibility(View.GONE);
                if (result == null) {
                } else {
                    dataList.clear();
                    data = JsonMananger.jsonToList(result, Bean_ListData.class);
                    dataList.addAll(data);
                    dataListAdapter.notifyDataSetInvalidated();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mPbDispersed.setVisibility(View.GONE);
                ToastUtil.showShort(getApplicationContext(), "数据加载失败！" + e.getMessage());
            }
        });
    }

    public void upCheckBox() {
        if (mRbButtonLeft.isChecked()) {
            houseAdd.setRentType("合租");
            mRbButtonLeft.setTextColor(getResources().getColor(R.color.white));
            mRbButtonRight.setTextColor(getResources().getColor(R.color.background_color));
        } else {
            houseAdd.setRentType("整租");
            mRbButtonRight.setTextColor(getResources().getColor(R.color.white));
            mRbButtonLeft.setTextColor(getResources().getColor(R.color.background_color));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            mTvAddress.setText(bundle.getString("Region"));
            mTvAddressMessage.setText(bundle.getString("addrDetali"));

            houseAdd.setAddrDetali(bundle.getString("addrDetali"));
            houseAdd.setRegion(bundle.getString("Region"));
            houseAdd.setLng(bundle.getDouble("Lng"));
            houseAdd.setLat(bundle.getDouble("Lat"));
        }
    }

    @Override
    public void getNums(int nums, int name) {
        mTvDispersedRoomsNum.setText(String.valueOf(nums) + "间");
        houseAdd.setRoomNumber(String.valueOf(nums));
    }

}
