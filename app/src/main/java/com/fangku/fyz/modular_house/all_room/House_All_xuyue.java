package com.fangku.fyz.modular_house.all_room;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 续约
 * Created by   jie.wang
 * Date: 2016/9/22
 * Time: 15:04
 */
public class House_All_xuyue extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;

    @Bind(R.id.et_xuyue_zujin)
    EditText mEtXuyueZujin;
    @Bind(R.id.et_xuyue_yajin)
    EditText mEtXuyueYajin;
    @Bind(R.id.tv_xuyue_qizuriqi)
    TextView mTvXuyueQizuriqi;
    @Bind(R.id.ll_xuyue_qizuqiri)
    LinearLayout mLlXuyueQizuriqi;
    @Bind(R.id.tv_xuyue_xuyueriqi)
    TextView mTvXuyueXuyueriqi;
    @Bind(R.id.ll_xuyue_xuyueriqi)
    LinearLayout mLlXuyueXuyueriqi;
    @Bind(R.id.tv_xuyue_old_yajin)
    TextView mTvXuyueOldYajin;
    @Bind(R.id.tv_xuyue_old_yajin_danwei)
    TextView mTvXuyueOldYajinDanwei;
    @Bind(R.id.tv_xuyue_old_zujin)
    TextView mTvXuyueOldZujin;
    @Bind(R.id.tv_xuyue_old_zujin_danwei)
    TextView mTvXuyueOldZujinDanwei;
    @Bind(R.id.tv_xuyue_old_riqi)
    TextView mTvXuyueOldRiqi;
    @Bind(R.id.bt_xuyue_ok)
    Button mBtXuyueOk;


    //设置年月日
    int mYear = 2016, mMonth = 8, mDay = 1;
    @Bind(R.id.et_xuyue_zuqi)
    EditText mEtXuyueZuqi;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.et_xuyue_zujin_zhouqi)
    EditText mEtXuyueZujinZhouqi;
    @Bind(R.id.et_xuyue_qitafeiyong)
    EditText mEtXuyueQitafeiyong;
    @Bind(R.id.tv_xuyue_get_house)
    TextView mTvXuyueGetHouse;
    @Bind(R.id.tv_xuyue_get_room)
    TextView mTvXuyueGetRoom;


    private String mHouseName;
    private String mHouseId;

    private String mRoomId;
    private String mRoomName;
    private String mHetongId;

    public static void launch(Context mContext, String houseId, String hetongID, String houseName, String roomID, String roomName) {
        Intent mIntent = new Intent(mContext, House_All_xuyue.class);
        mIntent.putExtra("houseName", houseName);
        mIntent.putExtra("roomId", roomID);
        mIntent.putExtra("roomName", roomName);
        mIntent.putExtra("houseID", houseId);
        mIntent.putExtra("hetongID", hetongID);

        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_xuyue;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {


        Intent intent = getIntent();
        mHouseName = intent.getStringExtra("houseName");
        mRoomId = intent.getStringExtra("roomId");
        mRoomName = intent.getStringExtra("roomName");
        mHouseId = intent.getStringExtra("houseID");
        mHetongId = intent.getStringExtra("hetongID");

        mTvTitle.setText("续约");
        mTvXuyueGetHouse.setText(mHouseName);
        mTvXuyueGetRoom.setText(mRoomName);

        mImageView.setVisibility(View.GONE);
        initEdlistener();
        //关于日期的设置开始
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        mTvXuyueQizuriqi.setText(df.format(new Date()));
        mEtXuyueZuqi.setText("3");

        mTvXuyueXuyueriqi.setText(CommonUtil.setTime(mEtXuyueZuqi.getText().toString(), mTvXuyueQizuriqi.getText().toString()));


    }

    @Override
    public void getData() {

    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }

    //监控文字输入状态
    private void initEdlistener() {

        mEtXuyueZujin.addTextChangedListener(
                new TextWatcher() {

                    //退格时执行此方法,长度为0时不执行   ,输入时也执行
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
                    }

                    //输入时时执行此方法,长度为0时不执行
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String a = s.toString().trim();
                        if (a.equals("")) {
                            a = "";
                        }
                        mEtXuyueYajin.setText(a);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() == 0) {
                            mEtXuyueYajin.setText("");
                        }
                    }
                }

        );

        mEtXuyueZuqi.addTextChangedListener(
                new TextWatcher() {

                    //退格时执行此方法,长度为0时不执行   ,输入时也执行
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
                    }

                    //输入时时执行此方法,长度为0时不执行
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String a = s.toString().trim();
                        if ("".equals(a)) {
                            return;
                        }
                        mTvXuyueXuyueriqi.setText(CommonUtil.setTime(a, mTvXuyueQizuriqi.getText().toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.i("fffffff", "after");
                        if (s.length() == 0) {
                            mTvXuyueXuyueriqi.setText(mTvXuyueQizuriqi.getText().toString());
                        }
                    }
                }

        );
    }


    @OnClick({R.id.btn_back, R.id.ll_xuyue_qizuqiri, R.id.ll_xuyue_xuyueriqi, R.id.bt_xuyue_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_xuyue_qizuqiri:
                time(mTvXuyueQizuriqi);

                break;
            case R.id.ll_xuyue_xuyueriqi:


                break;
            case R.id.bt_xuyue_ok:
                saveRenewContract();

                break;
        }
    }

    /**
     * 日期弹出框
     */
    public void time(final TextView textView) {
        //创建DatePickerDialog对象
        DatePickerDialog dpd = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                //更新日期

                String mDate = mYear + "-" + (mMonth < 9 ? "0" + String.valueOf(mMonth + 1) : (mMonth + 1)) + "-" + mDay;
                textView.setText(mDate);
                if (textView.getId() == R.id.tv_xuyue_qizuriqi) {
                    mTvXuyueXuyueriqi.setText(CommonUtil.setTime(mEtXuyueZuqi.getText().toString(), mTvXuyueQizuriqi.getText().toString()));

                }
            }
        }, mYear, mMonth, mDay);
        dpd.show();//显示DatePickerDialog组件
    }

    /**
     * 保存续约内容
     */
    public void saveRenewContract() {

        Check();
        mLoadingDialog.show(mContext);
        Map<String, String> map = new HashMap<>();
        map.put("sysId", mHetongId);//合同Id
        map.put("houseId", "11");//房屋Id
        map.put("roomId", mRoomId);//房间Id`
        map.put("rent", mEtXuyueZujin.getText().toString());//租金
        map.put("deposit", mEtXuyueYajin.getText().toString());//押金
        map.put("startDate", mTvXuyueQizuriqi.getText().toString());//起租日期
        map.put("endDate", mTvXuyueXuyueriqi.getText().toString());//结束日期
        map.put("chargeCycle", mEtXuyueQitafeiyong.getText().toString());//收费项目周期
        map.put("rentCycle", mEtXuyueZujinZhouqi.getText().toString());//收租周期


        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.SAVE_RENEW_CONTRACT, map)
                .execute(new Bean_Callback<BaseResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BaseResponse response, String message) {
                        finish();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });


    }

    private void Check() {
        if (TextUtils.isEmpty(mEtXuyueZujin.getText().toString())) {
            ToastUtil.showShort(mContext, "租金不能为空!");
            return;
        }


        if (TextUtils.isEmpty(mEtXuyueYajin.getText().toString())) {
            ToastUtil.showShort(mContext, "押金不能为空!");
            return;
        }

        if (TextUtils.isEmpty(mEtXuyueZujinZhouqi.getText().toString())) {
            ToastUtil.showShort(mContext, "租金的收费周期不能为空!");
            return;
        }

        if (TextUtils.isEmpty(mEtXuyueQitafeiyong.getText().toString())) {
            ToastUtil.showShort(mContext, "其他费用的收费周期不能为空!");
            return;
        }


    }


}
