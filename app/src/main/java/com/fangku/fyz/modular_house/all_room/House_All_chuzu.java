package com.fangku.fyz.modular_house.all_room;/**
 * Created by 67343 on 2016/8/5.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.widget.AllListView;
import com.fangku.fyz.R;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_House_Chuzu;
import com.fangku.fyz.modular_house.bean.Bean_House_All_Roomer;
import com.fangku.fyz.modular_hydropower.Bean_Hydropower;
import com.fangku.fyz.modular_me.real.Me_Real;
import com.fangku.fyz.modular_me.real.Me_Real_Back;
import com.fangku.fyz.modular_me.real.Me_Real_Front;
import com.fangku.fyz.util.CommonUtil;
import com.fangku.fyz.util.MyBaseActivity;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 出租
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_All_chuzu extends MyBaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_heyue_realname)
    EditText mEtHeyueRealname;
    @Bind(R.id.et_heyue_card_num)
    EditText mEtHeyueCardNum;
    @Bind(R.id.et_heyue_phone)
    EditText mEtHeyuePhone;
    @Bind(R.id.et_heyue_work_map)
    EditText mEtHeyueWorkMap;
    @Bind(R.id.et_heyue_money1)
    EditText mEtHeyueMoney1;
    @Bind(R.id.et_heyue_money2)
    EditText mEtHeyueMoney2;
    @Bind(R.id.rb_heyue_man)
    RadioButton mRbHeyueMan;
    @Bind(R.id.rb_heyue_woman)
    RadioButton mRbHeyueWoman;
    @Bind(R.id.lv_heyue)
    AllListView mLvHeyue;
    @Bind(R.id.sc_heyue)
    ScrollView mScHeyue;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.tv_heyue_get_house)
    TextView mTvHeyueGetHouse;
    @Bind(R.id.tv_heyue_get_room)
    TextView mTvHeyueGetRoom;
    @Bind(R.id.iv_heyeu_zhengmian)
    ImageView mIvHeyeuZhengmian;
    @Bind(R.id.iv_heyeu_fangmian)
    ImageView mIvHeyeuFangmian;
    @Bind(R.id.bt_heyue_next)
    Button mBtHeyueNext;

    String roomId;
    String houseId;
    String landlordCardId;
    String landlordId;
    String contractId;
    boolean tag;

    Adapter_House_Chuzu mAdapter;
    public static List<Bean_Hydropower.DataEntity> mBiao = new ArrayList<>();
    Bean_House_All_Roomer.DataEntity roomer = new Bean_House_All_Roomer.DataEntity();

    public static void launch(String house, String number, String roomId, String houseId, String contractId, String landlordId, String landlordCardId, Context mContext) {
        Intent mIntent = new Intent(mContext, House_All_chuzu.class);
        mIntent.putExtra("house", house);
        mIntent.putExtra("number", number);
        mIntent.putExtra("roomId", roomId);
        mIntent.putExtra("houseId", houseId);
        mIntent.putExtra("landlordId", landlordId);
        mIntent.putExtra("landlordCardId", landlordCardId);
        mIntent.putExtra("contractId", contractId);
        mContext.startActivity(mIntent);

    }

    @Override
    public int bindLayout() {
        return R.layout.house_all_heyue;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mTvTitle.setText("出租");
        Intent intent = getIntent();
        String house = intent.getStringExtra("house");
        String number = intent.getStringExtra("number");
        roomId = intent.getStringExtra("roomId");
        houseId = intent.getStringExtra("houseId");
        landlordCardId = intent.getStringExtra("landlordCardId");
        landlordId = intent.getStringExtra("landlordId");
        contractId = intent.getStringExtra("contractId");

        mTvHeyueGetHouse.setText(house);
        mTvHeyueGetRoom.setText(number);
        mImageView.setVisibility(View.GONE);
        mScHeyue.smoothScrollTo(0, 0);

        //抄表信息写入
        Bean_Hydropower.DataEntity hydropower = new Bean_Hydropower.DataEntity();
        hydropower.setMeterName("水表");
        hydropower.setMeterType("W");
        mBiao.add(hydropower);
        Bean_Hydropower.DataEntity hydropower2 = new Bean_Hydropower.DataEntity();
        hydropower2.setMeterName("电表");
        hydropower2.setMeterType("E");
        mBiao.add(hydropower2);

        initEdlistener();
        mAdapter = new Adapter_House_Chuzu(House_All_chuzu.this, mBiao);
        mLvHeyue.setAdapter(mAdapter);

    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.ll_heyue_saomiao, R.id.bt_heyue_next, R.id.iv_heyeu_zhengmian, R.id.iv_heyeu_fangmian})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_heyeu_zhengmian:
                House_All_Look_Photo.launch(mContext, "正");

                break;
            case R.id.iv_heyeu_fangmian:
                House_All_Look_Photo.launch(mContext, "反");


                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_heyue_saomiao:
                takePhoto();
                break;
            case R.id.bt_heyue_next:
                addRoomer();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if (Me_Real.front != null) {
            mIvHeyeuZhengmian.setImageBitmap(Me_Real.front);
//            new BitmapFilter().execute(front, b);
            File file_Front = CommonUtil.saveBitmapFile(Me_Real.front);
        }
        if (Me_Real.back != null) {
            mIvHeyeuFangmian.setImageBitmap(Me_Real.back);
//            new BitmapFilter().execute(back, b);
            File file_Back = CommonUtil.saveBitmapFile(Me_Real.back);
        }

    }

    /**
     * 新增租客
     */
    public void addRoomer() {
        String sex;
        if (mRbHeyueMan.isChecked()) {
            sex = "男";
        } else {
            sex = "女";
        }
        if (!"".equals(mEtHeyueRealname.getText().toString()) && !"".equals(mEtHeyuePhone.getText().toString()) && !"".equals(mEtHeyueCardNum.getText().toString())) {
            mLoadingDialog.show(mContext);
            Map<String, String> map = new HashMap<>();
            map.put("landlordId", landlordId);
            map.put("houseId", houseId);
            map.put("roomId", roomId);
            map.put("idCardNo", mEtHeyueCardNum.getText().toString());
            map.put("realName", mEtHeyueRealname.getText().toString());
            map.put("roomerMoblie", mEtHeyuePhone.getText().toString());
            map.put("workPlace", mEtHeyueWorkMap.getText().toString());
            map.put("gender", sex);
            map.put("isCheck", "n");
            map.put("isMaster", "y");

            PostUtil postUtil = new PostUtil();
            postUtil.Post_Bean(Url_final.ADD_ROOMER, map)
                    .execute(new Bean_Callback<Bean_House_All_Roomer>(mContext) {
                        @Override
                        protected void onSuccess_Code200(Bean_House_All_Roomer response, String message) {
                            //设焦点事件
                            mEtHeyueMoney2.requestFocus();
                            String rent = mEtHeyueMoney1.getText().toString();
                            String deposit = mEtHeyueMoney2.getText().toString();
                            if (rent.length() == 0) {
                                ToastUtil.showShort(mContext, "请输入租金");
                            } else if (deposit.length() == 0) {
                                ToastUtil.showShort(mContext, "请输入押金");
                            } else if (mBiao.get(0).getThisScale() == 0) {
                                ToastUtil.showShort(mContext, "请输入水表数");
                            } else if (mBiao.get(1).getThisScale() == 0) {
                                ToastUtil.showShort(mContext, "请输入电表数");
                            } else {
                                House_All_chuzu2.launch(House_All_chuzu.this, rent, deposit, response.getData(), roomId, houseId, landlordCardId);
                            }
                        }

                        @Override
                        protected void onOver() {
                            mLoadingDialog.cancel();
                        }
                    });

        } else {
            ToastUtil.showShort(mContext, "请输入完整租客信息");
        }

    }


    /**
     * 选择拍照弹出框
     */
    public void takePhoto() {
        final String[] stringItems = {"拍摄正面", "拍摄背面"};
        final ActionSheetDialog dialog = new ActionSheetDialog(House_All_chuzu.this, stringItems, null);
        dialog.title("请选择正反面")//
                .titleTextSize_SP(14.5f)//
                .itemHeight(55)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //正面
                        Me_Real_Front.launch(House_All_chuzu.this);
                        break;
                    case 1://背面
                        Me_Real_Back.launch(House_All_chuzu.this);
                        break;

                }
                dialog.dismiss();
            }
        });
    }


    //监控文字输入状态
    private void initEdlistener() {

        mEtHeyueMoney1.addTextChangedListener(
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
                        if (a.equals("")) {
                            a = "";
                        }
                        mEtHeyueMoney2.setText(a);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.i("fffffff", "after");
                        if (s.length() == 0) {
                            mEtHeyueMoney2.setText("");
                        }
                    }
                }

        );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Me_Real.back = null;
        Me_Real.front = null;
        mBiao = new ArrayList<>();
        System.gc();
    }


}
