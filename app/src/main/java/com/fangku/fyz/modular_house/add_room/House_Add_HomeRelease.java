package com.fangku.fyz.modular_house.add_room;/**
 * Created by 67343 on 2016/8/11.
 */

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.DensityUtil;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.utils.PhotoUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.constant.Static_Login;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_GridView_DataZhouBian;
import com.fangku.fyz.modular_house.bean.Bean_House_RoomData;
import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;
import com.fangku.fyz.sql.Sql_Table_Bean_House_RoomData;
import com.fangku.fyz.sql.Sql_Table_Bean_Huose_Add;
import com.fangku.fyz.sql.Sql_Table_Bean_ListData_Add;
import com.fangku.fyz.sql.Sql_Table_Photo;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
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
 * 添加集中式房产的最后页面（房间信息页面）
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_Add_HomeRelease extends MyBaseActivity implements TextWatcher {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_home_woNums)
    EditText mEtHomeWoNums;
    @Bind(R.id.et_home_tingNums)
    EditText mEtHomeTingNums;
    @Bind(R.id.et_home_weiNums)
    EditText mEtHomeWeiNums;
    @Bind(R.id.et_home_yaNums)
    EditText mEtHomeYaNums;
    @Bind(R.id.et_home_fuNums)
    EditText mEtHomeFuNums;
    @Bind(R.id.tv_home_czTime)
    TextView mTvHomeCzTime;
    @Bind(R.id.nsgv_house_peitao)
    NoScrollGridView mNsgvHousePeitao;
    @Bind(R.id.iv_Home_shanjiao)
    ImageView mIvHomeShanjiao;
    @Bind(R.id.ll_homerelease_add_container)
    LinearLayout mLlHomereleaseAddContainer;
    @Bind(R.id.pb_hoomrelease)
    ProgressBar mPbHoomrelease;

    List<Bean_ListData> data;
    Bean_Huose_Add addData = new Bean_Huose_Add();

    List<Bean_ListData> datapeitao = new ArrayList<>();
    Adapter_GridView_DataZhouBian dataAdapter;

    List<Bean_House_RoomData> roomDatas = new ArrayList<>();

    Dialog_Add_HouseSuccess addHouseSuccess;

    Map<String, String> house = new HashMap<>();

    public static House_Add_HomeRelease homeRelease;

    public ArrayList<String> mShowAddress = new ArrayList<>();
    public ArrayList<String> mFouseImage;

    private static final int REQUESTIMGCODE = 0x013;
    private static final int SHOWADDRESSMAX = 9;

    UpLodionImages mUpLodionImages;

    String mating = "";//配套
    Bean_House_RoomData mRoomMessage;

    //设置年月日
    int mYear = 2016, mMonth = 8, mDay = 1;

    Sql_Table_Bean_House_RoomData tableBeanHouseRoomData;
    Sql_Table_Photo tablePhoto;
    Sql_Table_Bean_ListData_Add tableBeanListDataAdd;
    Sql_Table_Bean_Huose_Add table_bean_huose_add;

    @Bind(R.id.et_home_message)
    EditText mEtHomeMessage;

    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, House_Add_HomeRelease.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_add_home_release;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        tableBeanHouseRoomData = new Sql_Table_Bean_House_RoomData(this);
        tablePhoto = new Sql_Table_Photo(this);
        tableBeanListDataAdd = new Sql_Table_Bean_ListData_Add(this);
        table_bean_huose_add = new Sql_Table_Bean_Huose_Add(this);

        List<Bean_House_RoomData> datas = tableBeanHouseRoomData.select();
        if (datas.size() > 0) {
            loadingSqlData(datas.get(0));
        }
        mEtHomeWoNums.addTextChangedListener(this);
        mEtHomeWeiNums.addTextChangedListener(this);
        mEtHomeTingNums.addTextChangedListener(this);
        mEtHomeYaNums.addTextChangedListener(this);
        mEtHomeFuNums.addTextChangedListener(this);
        mEtHomeMessage.addTextChangedListener(this);

        homeRelease = this;
        mTvTitle.setText("房间信息");
        addHouseSuccess = new Dialog_Add_HouseSuccess();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        mTvHomeCzTime.setText(str);

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

        mFouseImage = House_Add_Focus.mShowAddress;

        dataAdapter = new Adapter_GridView_DataZhouBian(this, datapeitao);
        mNsgvHousePeitao.setAdapter(dataAdapter);
    }

    /**
     * 加载上次未完成的数据
     *
     * @param bean_house_roomData
     */
    private void loadingSqlData(Bean_House_RoomData bean_house_roomData) {
        mRoomMessage = bean_house_roomData;
        mEtHomeWoNums.setText(bean_house_roomData.getRoomNumber());
        mEtHomeWeiNums.setText(bean_house_roomData.getToiletNumber());
        mEtHomeTingNums.setText(bean_house_roomData.getHallNumber());
        mEtHomeYaNums.setText(bean_house_roomData.getDepositNumber());
        mEtHomeFuNums.setText(bean_house_roomData.getPayNumber());
        mTvHomeCzTime.setText(bean_house_roomData.getCheckDate());
        mEtHomeMessage.setText(bean_house_roomData.getRoomDesc());

        List<Sql_Table_Photo.mPhotoClass> imgData = tablePhoto.select();
        if (imgData.size() > 0) {
            mShowAddress.clear();
            for (int i = 0; i < imgData.size(); i++) {
                if ("home".equals(imgData.get(i).getmStyle())) {
                    mShowAddress.add(imgData.get(i).getmPhotoName());
                }
            }
            loadingImage(mShowAddress);
        }

    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.btn_back, R.id.bt_Home_commit, R.id.rl_Home_peitao, R.id.iv_homerelease_add, R.id.ll_homerelease_set_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_Home_commit:
                setRoomData();
                break;
            case R.id.rl_Home_peitao:
                if (mNsgvHousePeitao.getVisibility() != View.VISIBLE) {
                    mNsgvHousePeitao.setVisibility(View.VISIBLE);
                    mIvHomeShanjiao.setImageResource(R.mipmap.xiala_gray_icon_fang);
                    if (data == null) {
                        addpeitaoData();
                        mPbHoomrelease.setVisibility(View.VISIBLE);
                    }
                } else {
                    mNsgvHousePeitao.setVisibility(View.GONE);
                    mIvHomeShanjiao.setImageResource(R.mipmap.xiala_gray_icon);
                }
                break;
            case R.id.iv_homerelease_add:
                selectImg();
                break;
            case R.id.ll_homerelease_set_time:
                time(mTvHomeCzTime);
                break;
        }
    }

    private void addpeitaoData() {
        HttpUtil http = new HttpUtil();
        http.doPost(Url_final.GET_BASELIST + "RoomSupport/" + "false", new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mPbHoomrelease.setVisibility(View.GONE);
                if (result == null) {
                    ToastUtil.showShort(getApplicationContext(), "数据获取失败！");
                } else {
                    datapeitao.clear();
                    data = JsonMananger.jsonToList(result, Bean_ListData.class);
                    datapeitao.addAll(data);
                    dataAdapter.notifyDataSetInvalidated();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mPbHoomrelease.setVisibility(View.GONE);
                ToastUtil.showShort(getApplicationContext(), R.string.will_dopost_onFailed);
            }
        });
    }

    private void setRoomData() {
        mLoadingDialog.show(this, "正在创建房产中.....");
        addData = House_Add_Focus.mRoomData;
        String[] roomImageID = new String[9];

        int roomNums = 0;
        for (int i = 0; i < datapeitao.size(); i++) {
            if (datapeitao.get(i).isCheck()) {
                mating += datapeitao.get(i).getName() + "|";
            }
        }
        //添加房间图片
        String imgs = "image";

        if (mShowAddress.size() > 0) {

            mUpLodionImages = new UpLodionImages(this, mShowAddress.size());

            for (int i = 0; i < mShowAddress.size(); i++) {
                mUpLodionImages.doUp(mShowAddress.get(i), i + 1);
                mUpLodionImages.setImageUPLinstent(new UpLodionImages.ImageUPLinstent() {
                    @Override
                    public void getImgaeID(String imagID, boolean[] isLast, int position) {
                        roomImageID[position - 1] = imagID;
                        for (int i = 0; i < isLast.length; i++) {
                            if (!isLast[i]) {
                                break;
                            }
                            if (i + 1 == isLast.length) {
                                setOtherData(roomNums, roomImageID);
                            }
                        }
                    }
                });
            }
        } else {
            setOtherData(roomNums, roomImageID);
        }

    }

    public void setOtherData(int roomNums, String[] roomImageID) {
        for (int i = 0; i < House_Add_Focus.mRoomData.getFloorsAndRooms().size(); i++) {
            for (int j = 0; j < House_Add_Focus.mRoomData.getFloorsAndRooms().get(i).getData().size(); j++) {
                roomNums++;
                Bean_House_RoomData mRoom = new Bean_House_RoomData();
                mRoom.setRoomNo(String.valueOf(House_Add_Focus.mRoomData.getFloorsAndRooms().get(i).getData().get(j).getRoom()));
                mRoom.setHouseLeve(String.valueOf(House_Add_Focus.mRoomData.getFloorsAndRooms().get(i).getFloor()));
                mRoom.setState("未出租");
                mRoom.setRoomNumber(mEtHomeWoNums.getText().toString());
                mRoom.setToiletNumber(mEtHomeWeiNums.getText().toString());
                mRoom.setHallNumber(mEtHomeTingNums.getText().toString());
                mRoom.setCheckDate(mTvHomeCzTime.getText().toString());
                mRoom.setDepositNumber(mEtHomeYaNums.getText().toString());
                mRoom.setPayNumber(mEtHomeFuNums.getText().toString());
                mRoom.setMating(mating);
                mRoom.setImage1(roomImageID[0]);
                mRoom.setImage2(roomImageID[1]);
                mRoom.setImage3(roomImageID[2]);
                mRoom.setImage4(roomImageID[3]);
                mRoom.setImage5(roomImageID[4]);
                mRoom.setImage6(roomImageID[5]);
                mRoom.setImage7(roomImageID[6]);
                mRoom.setImage8(roomImageID[7]);
                mRoom.setImage9(roomImageID[8]);
                roomDatas.add(mRoom);
            }
        }

        house.put("addrDetail", addData.getAddrDetali().toString());
        house.put("region", addData.getRegion().toString());
        house.put("houseName", addData.getHouseName().toString());
        house.put("houseLevel", addData.getHouseLevel().toString());
        house.put("roomNumber", String.valueOf(roomNums).toString());
        house.put("lng", addData.getLng().toString().toString());
        house.put("lat", addData.getLat().toString().toString());
        house.put("houseType", addData.getHouseType().toString());
        house.put("rentType", addData.getRentType().toString());
        house.put("isLift", addData.getIsLift().toString());
        house.put("emptyNumber", String.valueOf(roomNums).toString());
        String matingFouse = "";
        for (int i = 0; i < House_Add_Focus.focusDataList.size(); i++) {
            if (House_Add_Focus.focusDataList.get(i).isCheck()) {
                matingFouse += House_Add_Focus.focusDataList.get(i).getName() + "|";
            }
        }
        house.put("arroundSupport", matingFouse.toString());

        house.put("jroomList", JsonMananger.beanToJson(roomDatas).toString());


        house.put("jhChargeList", JsonMananger.beanToJson(House_Add_Focus.mHuoseCharge));

        String houseData = "房产名：" + addData.getHouseName() + "\n楼层数" + addData.getHouseLevel() + "\n总房间数：" + String.valueOf(roomNums) + "\n地址：" + addData.getRegion() + addData.getAddrDetali();

        final String finalHouseData = houseData;

//        uPLodinImgs();
        if (mFouseImage.size() > 0) {
            //添加房产图片
            String imgs2 = "image";

            mUpLodionImages = new UpLodionImages(this, mFouseImage.size());

            for (int i = 0; i < mFouseImage.size(); i++) {
                Log.i("test", "LoginImage" + i);
                mUpLodionImages.doUp(mFouseImage.get(i), i + 1);
                mUpLodionImages.setImageUPLinstent(new UpLodionImages.ImageUPLinstent() {
                    @Override
                    public void getImgaeID(String imagID, boolean[] isLast, int position) {
                        house.put(imgs2 + String.valueOf(position), imagID);
                        Log.i("test", "imagID:" + imagID + "  " + isLast);
                        for (int i = 0; i < isLast.length; i++) {
                            if (!isLast[i]) {
                                break;
                            }
                            if (i + 1 == isLast.length) {
                                doIntent(finalHouseData);
                            }
                        }
                    }
                });
            }
        } else {
            doIntent(finalHouseData);
        }
    }

    public void doIntent(String data) {
        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.ADD_HOUSE2, house).execute(new Bean_Callback<BeanResponse>(mContext) {
            @Override
            protected void onSuccess_Code200(BeanResponse response, String message) {
                Static_Login.updateHouseName(mContext, result -> {
                });
                addHouseSuccess.show(House_Add_HomeRelease.this, data);
                table_bean_huose_add.clear();

            }

            @Override
            protected void onOver() {
                mLoadingDialog.cancel();
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
            loadingImage(mShowAddress);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mBackMessage();
        } catch (RuntimeException e) {
            LogUtil.i(e.getMessage());
            finish();
        }


    }

    /**
     * 当点击返回时/关闭时调用保存
     */
    private void mBackMessage() {
        List<Sql_Table_Photo.mPhotoClass> photoClass = tablePhoto.select();
        if (photoClass.size() <= 0) {
            for (int i = 0; i < mShowAddress.size(); i++) {
                tablePhoto.insert(mShowAddress.get(i), "focus");
            }
        }
        for (int j = 0; j < photoClass.size(); j++) {
            if (photoClass.get(j).getmStyle().equals("focus")) {
                break;
            }
            if (j == photoClass.size() - 1) {
                for (int i = 0; i < mShowAddress.size(); i++) {
                    tablePhoto.insert(mShowAddress.get(i), "focus");
                }
            }
        }

        for (int i = 0; i < datapeitao.size(); i++) {
            if (datapeitao.get(i).isCheck())
                tableBeanListDataAdd.insert(datapeitao.get(i));
        }

        tableBeanHouseRoomData.clear();
        tableBeanHouseRoomData.insert(mRoomMessage);
    }

    private void loadingImage(ArrayList<String> mShowAddress) {
        mLlHomereleaseAddContainer.removeAllViews();
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
                    Intent intent = new Intent(House_Add_HomeRelease.this, House_Add_Preview.class);
                    intent.putStringArrayListExtra("pics", mShowAddress);
                    intent.putExtra("index", finalI);
                    House_Add_HomeRelease.this.startActivity(intent);

                }
            });
            Glide.with(this).load(new File(imgPath)).override(widthAndHeight, widthAndHeight).into(iv);
            mLlHomereleaseAddContainer.addView(iv);
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
        mLlHomereleaseAddContainer.addView(iv);
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
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                //更新日期

                String mDate = mYear + "-" + (mMonth < 9 ? "0" + String.valueOf(mMonth + 1) : (mMonth + 1)) + "-" + mDay;
                textView.setText(mDate);
            }
        }, mYear, mMonth, mDay);
        dpd.show();//显示DatePickerDialog组件
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mRoomMessage = new Bean_House_RoomData();
        mRoomMessage.setRoomNumber(mEtHomeWoNums.getText().toString());
        mRoomMessage.setToiletNumber(mEtHomeWeiNums.getText().toString());
        mRoomMessage.setHallNumber(mEtHomeTingNums.getText().toString());
        mRoomMessage.setCheckDate(mTvHomeCzTime.getText().toString());
        mRoomMessage.setDepositNumber(mEtHomeYaNums.getText().toString());
        mRoomMessage.setPayNumber(mEtHomeFuNums.getText().toString());
        mRoomMessage.setRoomDesc(mEtHomeMessage.getText().toString());
    }
}
