package com.fangku.fyz.modular_house.add_room;/**
 * Created by 67343 on 2016/8/12.
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.KeyBoardUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_Houes_Add_address;
import com.fangku.fyz.modular_house.bean.Bean_House_Add_GetAddress;
import com.fangku.fyz.util.MyBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_Get_Address extends MyBaseActivity implements Adapter_Houes_Add_address.ListViwLineOnClick {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_Select_Address)
    EditText mEtSelectAddress;
    @Bind(R.id.ll_select)
    LinearLayout mLlSelect;
    @Bind(R.id.lv_house_add_address)
    ListView mLvHouseAddAddress;


    Adapter_Houes_Add_address mAddressAdapter;
    List<Bean_House_Add_GetAddress.DataBean> mAddressList = new ArrayList<>();
    String mCity = "厦门市";

    //定位

    @Override
    public int bindLayout() {
        return R.layout.house_get_address;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("地址搜索");
//        mCity = LocationUtil.getCNBylocation(this);

        mAddressAdapter = new Adapter_Houes_Add_address(this, mAddressList);
        mAddressAdapter.setListViwLineOnClickListener(this);
        mLvHouseAddAddress.setAdapter(mAddressAdapter);
//        Log.i("Test", mCity + "");
//        if (TextUtils.isEmpty(mCity)) {
//            ToastUtil.showShort(mContext, "定位失败。。。。");
////            finish();
//        }
    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.im_clear_et, R.id.ll_select, R.id.bt_focus_clearAddress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.im_clear_et:
                mEtSelectAddress.setText("");
                break;
            case R.id.ll_select:
                KeyBoardUtil.closeKeybord(mEtSelectAddress, mContext);
                mLoadingDialog.show(this, "正在查询 ヽ(ｏ`皿′ｏ)ﾉ");
                selectAddress();
                break;
            case R.id.bt_focus_clearAddress:
                break;
        }
    }

    private void selectAddress() {
        if (!TextUtils.isEmpty(mEtSelectAddress.getText().toString())) {
            mAddressList.clear();
            final String address = mEtSelectAddress.getText().toString().trim();

            Map<String, String> map = new HashMap<>();
            map.put("keyWord", address);
            map.put("region", mCity + "");
            PostUtil postUtil=new PostUtil();
            postUtil.Post_Bean(Url_final.GET_ADDRESS, map).execute(new BeanList_Callback<Bean_House_Add_GetAddress>(mContext) {
                @Override
                protected void onSuccess_Code200(Bean_House_Add_GetAddress mAddress, String message) {
                    mAddressList.addAll(mAddress.getData());
                    mAddressAdapter.notifyDataSetInvalidated();
                }

                @Override
                protected void onOver() {
                    mLoadingDialog.cancel();
                }
            });

//
        }
    }

    @Override
    public void getData(Bean_House_Add_GetAddress.DataBean data) {

        Intent intent = new Intent();
        intent.putExtra("addrDetali", data.getDistrict() + "," + data.getName());
        intent.putExtra("Region", data.getCity());
        intent.putExtra("Lat", data.getLatitude());
        intent.putExtra("Lng", data.getLongitude());

        setResult(CONTEXT_INCLUDE_CODE, intent);
        finish();
    }

}
