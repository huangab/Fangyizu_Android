package com.fangku.fyz.modular_house.add_room;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.DensityUtil;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.PhotoUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.adapter.Adapter_GridView_DataZhouBian;
import com.fangku.fyz.modular_house.all_room.House_All_Main;
import com.fangku.fyz.modular_house.bean.Bean_House_Data;
import com.fangku.fyz.modular_house.bean.Bean_House_RoomData;
import com.fangku.fyz.sql.Sql_Table_Bean_House_RoomData;
import com.fangku.fyz.sql.Sql_Table_Bean_Huose_Add;
import com.fangku.fyz.sql.Sql_Table_Bean_ListData_Add;
import com.fangku.fyz.sql.Sql_Table_HouseMoney;
import com.fangku.fyz.sql.Sql_Table_Photo;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.widget.Dialog_Show;
import com.fangku.fyz.widget.UpLodionImages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by   jie.wang
 * Date: 2016/7/22
 * Time: 17:54
 */
public class House_Add_Room extends MyBaseActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.btn_back)
    Button mBtnBack;
    @Bind(R.id.tv_room_name)
    TextView mTvRoomName;
    @Bind(R.id.ed_roome_name)
    EditText mEdRoomeName;
    @Bind(R.id.et_room_woNums)
    EditText mEtRoomWoNums;
    @Bind(R.id.et_room_tingNums)
    EditText mEtRoomTingNums;
    @Bind(R.id.et_room_weiNums)
    EditText mEtRoomWeiNums;
    @Bind(R.id.et_room_yaNums)
    EditText mEtRoomYaNums;
    @Bind(R.id.et_room_fuNums)
    EditText mEtRoomFuNums;
    @Bind(R.id.tv_room_czTime)
    TextView mTvRoomCzTime;
    @Bind(R.id.et_room_louNum)
    EditText mEtRoomLouNum;
    @Bind(R.id.iv_room_add)
    ImageView mIvRoomAdd;
    @Bind(R.id.ll_roomrelease_add_container)
    LinearLayout mLlRoomreleaseAddContainer;
    @Bind(R.id.iv_room_shanjiao)
    ImageView mIvRoomShanjiao;
    @Bind(R.id.rl_room_peitao)
    RelativeLayout mRlRoomPeitao;
    @Bind(R.id.nsgv_room_peitao)
    NoScrollGridView mNsgvRoomPeitao;
    @Bind(R.id.bt_room_add_room_next)
    Button mBtRoomAddRoomNext;
    @Bind(R.id.pb_room)
    ProgressBar mPbRoom;

    Bean_House_RoomData roomData = new Bean_House_RoomData();

    public ArrayList<String> mShowAddress = new ArrayList<>();

    private static final int REQUESTIMGCODE = 0x013;
    private static final int SHOWADDRESSMAX = 9;

    List<Bean_ListData> data;
    List<Bean_ListData> peitaodata = new ArrayList<>();
    Adapter_GridView_DataZhouBian zhoubianAdapter;


    List<Bean_House_Data.DataBean> houseData = new ArrayList<>();


    UpLodionImages mUpLodionImages;

    //实例化数据库
    Sql_Table_Bean_Huose_Add table_bean_huose_add;
    Sql_Table_Bean_ListData_Add table_bean_listData_add;
    Sql_Table_Photo table_photo;
    Sql_Table_HouseMoney tableHouseMoney;
    Sql_Table_Bean_House_RoomData tableBeanHouseRoomData;
    //设置年月日
    int mYear = 2016, mMonth, mDay = 1;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, House_Add_Room.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_add_room;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("添加房间");
        zhoubianAdapter = new Adapter_GridView_DataZhouBian(this, peitaodata);
        mNsgvRoomPeitao.setAdapter(zhoubianAdapter);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        mTvRoomCzTime.setText(str);
        String[] str2 = str.split("-");
        mYear = Integer.valueOf(str2[0]);
        mMonth = Integer.valueOf(str2[1]) - 1;
        if (mMonth <= 0) {
            mMonth = 0;
        }
        if (mMonth >= 12) {
            mMonth = 11;
        }
        mDay = Integer.valueOf(str2[2].trim());

        table_bean_huose_add = new Sql_Table_Bean_Huose_Add(this);
        table_bean_listData_add = new Sql_Table_Bean_ListData_Add(this);
        table_photo = new Sql_Table_Photo(this);
        tableHouseMoney = new Sql_Table_HouseMoney(this);
        tableBeanHouseRoomData = new Sql_Table_Bean_House_RoomData(this);
    }

    @Override
    public void getData() {

    }


    @OnClick({R.id.btn_back, R.id.iv_room_add, R.id.rl_room_peitao, R.id.bt_room_add_room_next, R.id.ll_select_fangchan, R.id.ll_AddRoom_TimeSelect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_room_add:
                selectImg();
                break;
            case R.id.rl_room_peitao:
                if (mNsgvRoomPeitao.getVisibility() != View.VISIBLE) {
                    mNsgvRoomPeitao.setVisibility(View.VISIBLE);
                    mIvRoomShanjiao.setImageResource(R.mipmap.xiala_gray_icon_fang);
                    if (data == null) {
                        mPbRoom.setVisibility(View.VISIBLE);
                        addpeitaoData();
                    }
                } else {
                    mNsgvRoomPeitao.setVisibility(View.GONE);
                    mIvRoomShanjiao.setImageResource(R.mipmap.xiala_gray_icon);
                }
                break;
            case R.id.bt_room_add_room_next:
                createRoom();
                break;
            case R.id.ll_select_fangchan:
                selectFangChan();
                break;
            case R.id.ll_AddRoom_TimeSelect:
                time(mTvRoomCzTime);
                break;
        }


    }

    /**
     * 日期弹出框
     */
    public void time(TextView textView) {
        //创建DatePickerDialog对象
        DatePickerDialog dpd = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
                int mYear = year;
                int mMonth = monthOfYear;
                int mDay = dayOfMonth;
                //更新日期

                String mDate = mYear + "-" + (mMonth < 9 ? "0" + String.valueOf(mMonth + 1) : (mMonth + 1)) + "-" + mDay;
                textView.setText(mDate);
            }
        }, mYear, mMonth, mDay);
        dpd.show();//显示DatePickerDialog组件
    }

    private void createRoom() {
        if (TextUtils.isEmpty(roomData.getHouseId())) {
            ToastUtil.showShort(getApplicationContext(), "房产信息有误，请重新选择。");
            return;
        }
        if (TextUtils.isEmpty(mEdRoomeName.getText().toString())) {
            ToastUtil.showShort(getApplicationContext(), "请输入房间名。");
            return;
        }
        mLoadingDialog.show(this, "正在添加房间-. -");

        Map<String, String> map = new HashMap<>();
        map.put("HouseId", roomData.getHouseId());
        map.put("RoomNo", mEdRoomeName.getText().toString().trim());
        map.put("houseLeve", mEtRoomLouNum.getText().toString());
        map.put("state", "未出租");
        map.put("roomNumber", mEtRoomWoNums.getText().toString());
        map.put("toiletNumber", mEtRoomWeiNums.getText().toString());
        map.put("hallNumber", mEtRoomTingNums.getText().toString());
        map.put("depositNumber", mEtRoomYaNums.getText().toString());
        map.put("payNumber", mEtRoomFuNums.getText().toString());
        map.put("checkDate", mTvRoomCzTime.getText().toString());
        map.put("checkDate", mTvRoomCzTime.getText().toString());
        map.put("rent", "0");

        String mating = "";
        //添加已选好的配套
        for (int i = 0; i < peitaodata.size(); i++) {
            if (peitaodata.get(i).isCheck()) {
                mating += peitaodata.get(i).getName() + "|";
            }
        }
        map.put("mating", mating);


        if (mShowAddress.size() > 0) {
            //添加图片
            String imgs = "image";

            mUpLodionImages = new UpLodionImages(this, mShowAddress.size());

            for (int i = 0; i < mShowAddress.size(); i++) {
                final int finalI = i;
                mUpLodionImages.doUp(mShowAddress.get(i), i + 1);
                mUpLodionImages.setImageUPLinstent(new UpLodionImages.ImageUPLinstent() {
                    @Override
                    public void getImgaeID(String imagID, boolean[] isLast, int position) {
                        map.put(imgs + String.valueOf(position), imagID);
                        for (int i = 0; i < isLast.length; i++) {
                            if (!isLast[i]) {
                                break;
                            }
                            if (i + 1 == isLast.length) {
                                createRoomInten(map);
                            }
                        }
                    }
                });
            }
        }
        createRoomInten(map);
    }

    public void createRoomInten(Map<String, String> map) {
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.ADD_Room, map)
                .execute(new Bean_Callback<BeanResponse>(mContext) {
                    @Override
                    protected void onSuccess_Code200(BeanResponse response, String message) {
                        Log.i("test",message);
                        table_bean_huose_add.clear();
                        table_bean_listData_add.clear();
                        table_photo.clear();
                        tableHouseMoney.clear();
                        tableBeanHouseRoomData.clear();
                        Fragment_House.isUpdate = true;
                        House_All_Main.isNeedUpdate = true;
                        finish();
                    }

                    @Override
                    protected void onOver() {
                        mLoadingDialog.cancel();
                    }
                });


    }

    /**
     * 获取房产列表
     */
    private void selectFangChan() {
        Dialog_Show.showList(mContext, result -> {
            mTvRoomName.setText(MyApplication.stringItems[Integer.valueOf(result)]);//顶部选择房屋弹出框
            roomData.setHouseId(MyApplication.houseSysID[Integer.valueOf(result)]);
        });
    }

    private void addpeitaoData() {
        HttpUtil http = new HttpUtil();
        http.doPost(Url_final.GET_BASELIST + "RoomSupport/" + "false", new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mPbRoom.setVisibility(View.GONE);
                if (result == null) {
                    ToastUtil.showShort(getApplicationContext(), "数据获取失败！");
                } else {
                    peitaodata.clear();
                    data = JsonMananger.jsonToList(result, Bean_ListData.class);
                    peitaodata.addAll(data);
                    zhoubianAdapter.notifyDataSetInvalidated();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mPbRoom.setVisibility(View.GONE);
                ToastUtil.showShort(getApplicationContext(), "网络访问出错：" + e.getMessage());
            }
        });
    }


    private void selectImg() {
        PhotoUtil.openAlbumSelectPhotos(this, mShowAddress, SHOWADDRESSMAX, REQUESTIMGCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTIMGCODE && data != null) {
            mShowAddress = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mLlRoomreleaseAddContainer.removeAllViews();
            for (int i = 0; i < mShowAddress.size(); i++) {
                String imgPath = mShowAddress.get(i);
                ImageView iv = new ImageView(this);
                int widthAndHeight = DensityUtil.dp2px(this, 50);
                int padding = DensityUtil.dp2px(this, 3);
                iv.setPadding(padding, padding, 0, padding);
                final int finalI = i;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(House_Add_Room.this, House_Add_Preview.class);
                        intent.putStringArrayListExtra("pics", mShowAddress);
                        intent.putExtra("index", finalI);
                        House_Add_Room.this.startActivity(intent);

                    }
                });
                Glide.with(this).load(new File(imgPath)).override(widthAndHeight, widthAndHeight).into(iv);
                mLlRoomreleaseAddContainer.addView(iv);
            }
            ImageView iv = new ImageView(this);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectImg();
                }
            });
            int widthAndHeight = DensityUtil.dp2px(this, 50);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthAndHeight, widthAndHeight);
            params.leftMargin = DensityUtil.dp2px(this, 3);
            iv.setLayoutParams(params);
            iv.setImageResource(R.mipmap.tianjia_icon);
            mLlRoomreleaseAddContainer.addView(iv);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
