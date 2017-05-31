package com.fangku.fyz.modular_house.add_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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
import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.adapter.Adapter_GridView_DataZhouBian;
import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;
import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;
import com.fangku.fyz.sql.Sql_Table_Bean_House_RoomData;
import com.fangku.fyz.sql.Sql_Table_Bean_Huose_Add;
import com.fangku.fyz.sql.Sql_Table_Bean_ListData_Add;
import com.fangku.fyz.sql.Sql_Table_HouseMoney;
import com.fangku.fyz.sql.Sql_Table_Photo;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.fangku.fyz.widget.Dialog_Show;
import com.kyleduo.switchbutton.SwitchButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 添加集中式房源1
 * Created by bowen.ye
 * Date: 2016/7/18
 * Time: 16:08
 */
public class House_Add_Focus extends MyBaseActivity implements House_Add_Dialog.OnItemData {


    Context mContext = House_Add_Focus.this;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.bt_focus_next)
    Button mBtFocusNext;
    @Bind(R.id.ll_focus_show_container)
    LinearLayout mLlFocusShowContainer;
    @Bind(R.id.ll_focus_add_container)
    LinearLayout mLlFocusAddContainer;
    @Bind(R.id.nsgv_house_zoubian)
    NoScrollGridView mNsgvHouseZoubian;
    @Bind(R.id.et_house_louname)
    EditText mEtHouseLouname;
    @Bind(R.id.sb_focus_dianti)
    SwitchButton mSbFocusDianti;
    @Bind(R.id.iv_focus_shanjiao)
    ImageView mIvFocusShanjiao;
    @Bind(R.id.tv_focus_address)
    TextView mTvFocusAddress;
    @Bind(R.id.ll_focus_address)
    LinearLayout mLlFocusAddress;
    @Bind(R.id.tv_focus_address_sheng)
    TextView mTvFocusAddressSheng;
    @Bind(R.id.ll_focus_address_sheng)
    LinearLayout mLlFocusNowaddress;
    @Bind(R.id.tv_focus_louchengNums)
    TextView mTvFocusLouchengNums;
    @Bind(R.id.ll_focus_louchengNums)
    LinearLayout mLlFocusLouchengNums;
    @Bind(R.id.Tv_focus_RoomNums)
    TextView mTvFocusRoomNums;
    @Bind(R.id.ll_focus_RoomNums)
    LinearLayout mLlFocusRoomNums;
    @Bind(R.id.ll_focus_ZuoBianPeiTao)
    RelativeLayout mLlFocusZuoBianPeiTao;
    @Bind(R.id.pb_focus)
    ProgressBar mPbFocus;

    //***
    public static ArrayList<String> mShowAddress = new ArrayList<>();


    public static House_Add_Focus focus;

    private final int REQUESTIMGCODE = 0x013;
    private final int SHOWADDRESSMAX = 3;

    //***
    public static Bean_Huose_Add mRoomData = new Bean_Huose_Add();

    static List<Bean_House_All_RoomCharge> mHuoseCharge = new ArrayList<>();

    //数量选择
    House_Add_Dialog dialog;

    //周边相关
    Adapter_GridView_DataZhouBian focusDataListAdapter;

    //******
    public static List<Bean_ListData> focusDataList = new ArrayList<>();
    List<Bean_ListData> data;


    //实例化数据库
    Sql_Table_Bean_Huose_Add table_bean_huose_add;
    Sql_Table_Bean_ListData_Add table_bean_listData_add;
    Sql_Table_Photo table_photo;
    Sql_Table_HouseMoney tableHouseMoney;
    Sql_Table_Bean_House_RoomData tableBeanHouseRoomData;


    public static void launch(Context mContext) {
        Intent mIntent = new Intent(mContext, House_Add_Focus.class);
        mContext.startActivity(mIntent);
    }

    @Override
    public int bindLayout() {
        return R.layout.house_add_focus;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        focus = this;
        mTvTitle.setText("新增集中式房产");
        mRoomData.setHouseType("集中");
        mRoomData.setRentType("整租");
        focusDataListAdapter = new Adapter_GridView_DataZhouBian(this, focusDataList);
        mNsgvHouseZoubian.setAdapter(focusDataListAdapter);

        table_bean_huose_add = new Sql_Table_Bean_Huose_Add(this);
        table_bean_listData_add = new Sql_Table_Bean_ListData_Add(this);
        table_photo = new Sql_Table_Photo(this);
        tableHouseMoney = new Sql_Table_HouseMoney(this);
        tableBeanHouseRoomData = new Sql_Table_Bean_House_RoomData(this);

        Bean_Huose_Add data = table_bean_huose_add.select();
        if (data.getHouseName() != null && data.getAddrDetali() != null) {
            Dialog_Show.showTwoButton(this, "提示！", "您上次有未完成的操作，是否继续？", "放弃", "继续", new Only_CallBack() {
                @Override
                public void isSuccess(String result) {
                    if (result.equals("1")) {
                        //清空表中的数据
                        table_bean_listData_add.clear();
                        table_bean_huose_add.clear();
                        table_photo.clear();
                        tableHouseMoney.clear();
                        tableBeanHouseRoomData.clear();
                    } else
                        focusSetViewData(data);
                }
            });
        }
    }

    /**
     * 当用户选择继续上次操作时，将上次的数据加载到控件上
     *
     * @param data 数据库中上次的数据
     */
    private void focusSetViewData(Bean_Huose_Add data) {
        mRoomData = data;
        mEtHouseLouname.setText(data.getHouseName());
        mTvFocusAddress.setText(data.getAddrDetali());
        mTvFocusAddressSheng.setText(data.getRegion());
        mTvFocusLouchengNums.setText(data.getHouseLevel() + "层");
        mTvFocusRoomNums.setText(data.getRoomNumber() + "间");
        if (data.getIsLift().equals("false")) {
            mSbFocusDianti.setChecked(false);
        } else {
            mSbFocusDianti.setChecked(true);
        }

        //判断是否有图片,如果有则加载图片
        List<Sql_Table_Photo.mPhotoClass> photodata = table_photo.select();
        mShowAddress.clear();
        if (photodata.size() > 0) {
            for (int i = 0; i < photodata.size(); i++) {
                if ("focus".equals(photodata.get(i).getmStyle())) {
                    mShowAddress.add(photodata.get(i).getmPhotoName());
                }
            }
            setImage(mShowAddress);
        }
    }

    @Override
    public void getData() {
    }

    @OnClick({R.id.tv_title, R.id.btn_back, R.id.bt_focus_next, R.id.ll_focus_address, R.id.ll_focus_address_sheng, R.id.ll_focus_louchengNums, R.id.ll_focus_RoomNums, R.id.ll_focus_ZuoBianPeiTao, R.id.iv_focus_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.btn_back:
                mBackMessage();
                break;
            case R.id.bt_focus_next:
                doSelect();
                break;
            case R.id.iv_focus_add:
                selectImg();
                break;
            case R.id.ll_focus_address://点击详细地址
                Intent intent = new Intent(House_Add_Focus.this, House_Get_Address.class);
                this.startActivityForResult(intent, CONTEXT_INCLUDE_CODE);
                break;
            case R.id.ll_focus_address_sheng://点击区域
                break;
            case R.id.ll_focus_louchengNums://点击楼层数
                dialog = new House_Add_Dialog();
                dialog.SetOnItemListent(this);
                dialog.show(this, 1);
                break;
            case R.id.ll_focus_RoomNums://点击房间数
                dialog = new House_Add_Dialog();
                dialog.SetOnItemListent(this);
                dialog.show(this, 2);
                break;
            case R.id.ll_focus_ZuoBianPeiTao://点击周边配套
                if (mNsgvHouseZoubian.getVisibility() != View.VISIBLE) {
                    mNsgvHouseZoubian.setVisibility(View.VISIBLE);
                    mIvFocusShanjiao.setImageResource(R.mipmap.xiala_gray_icon_fang);
                    if (data == null) {
                        addZhoubianData();
                        mPbFocus.setVisibility(View.VISIBLE);
                    }
                } else {
                    mNsgvHouseZoubian.setVisibility(View.GONE);
                    mIvFocusShanjiao.setImageResource(R.mipmap.xiala_gray_icon);
                }
                break;
        }
    }

    private void addZhoubianData() {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doPost(Url_final.GET_BASELIST + "AroundSupport/" + "false", new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mPbFocus.setVisibility(View.GONE);
                if (result == null) {
                } else {
                    focusDataList.clear();
                    data = JsonMananger.jsonToList(result, Bean_ListData.class);
                    focusDataList.addAll(data);
                    focusDataListAdapter.notifyDataSetInvalidated();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mPbFocus.setVisibility(View.GONE);
                ToastUtil.showShort(getApplicationContext(), R.string.will_dopost_onFailed);
            }
        });
    }

    /**
     * 对用户输入信息进行检索
     */
    private void doSelect() {
        if (TextUtils.isEmpty(mRoomData.getAddrDetali()) || TextUtils.isEmpty(mRoomData.getRegion())) {
            ToastUtil.showShort(getApplicationContext(), "请选择地址！");
            return;
        }
        if (TextUtils.isEmpty(mEtHouseLouname.getText().toString().trim())) {
            ToastUtil.showShort(getApplicationContext(), "请输入楼名！");
            return;
        }
        if (TextUtils.isEmpty(mRoomData.getHouseLevel()) || TextUtils.isEmpty(mRoomData.getRoomNumber())) {
            ToastUtil.showShort(getApplicationContext(), "请选择楼层数目或单层房间数目！");
            return;
        }
        if (mSbFocusDianti.isChecked())
            mRoomData.setIsLift("1");
        else
            mRoomData.setIsLift("0");
        mRoomData.setHouseName(mEtHouseLouname.getText().toString().trim());

        mOutMessage();

        House_Money.launch(mContext, false);
    }


    private void selectImg() {
        PhotoUtil.openAlbumSelectPhotos(this, mShowAddress, SHOWADDRESSMAX, REQUESTIMGCODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTIMGCODE && data != null) {
            Log.i("test", "Image");
            mShowAddress = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            setImage(mShowAddress);
        }

        if (CONTEXT_INCLUDE_CODE == requestCode) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                mRoomData.setRegion(bundle.getString("Region"));
                mRoomData.setAddrDetali(bundle.getString("addrDetali"));
                mRoomData.setLng(bundle.getDouble("Lng"));
                mRoomData.setLat(bundle.getDouble("Lat"));
                mTvFocusAddress.setText(mRoomData.getAddrDetali());
                mTvFocusAddressSheng.setText(mRoomData.getRegion());
            }
        }
    }

    @Override
    public void getNums(int nums, int name) {
        switch (name) {
            case 1:
                mTvFocusLouchengNums.setText(String.valueOf(nums) + "层");
                mRoomData.setHouseLevel(String.valueOf(nums));
                break;
            case 2:
                mTvFocusRoomNums.setText(String.valueOf(nums) + "间");
                mRoomData.setRoomNumber(String.valueOf(nums));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mBackMessage();
        }
        return false;
    }

    private void mBackMessage() {
        Dialog_Show.showTwoButton(mContext, "提示", "是否要放弃新建？", "否", "是", new Only_CallBack() {
            @Override
            public void isSuccess(String result) {
                if (result.equals("2")) {
                    finish();
                }
            }
        });
    }

    private void setImage(ArrayList<String> mShowAddress) {
        mLlFocusAddContainer.removeAllViews();
        for (int i = 0; i < mShowAddress.size(); i++) {
            String imgPath = mShowAddress.get(i);
            ImageView iv = new ImageView(this);
            int widthAndHeight = DensityUtil.dp2px(this, 50);
            int padding = DensityUtil.dp2px(this, 3);
            iv.setPadding(padding, padding, 0, padding);
            final int finalI = i;
            iv.setOnClickListener(v -> {
                Intent intent = new Intent(House_Add_Focus.this, House_Add_Preview.class);
                intent.putStringArrayListExtra("pics", mShowAddress);
                intent.putExtra("index", finalI);
                House_Add_Focus.this.startActivity(intent);

            });
            Glide.with(this).load(new File(imgPath)).centerCrop().override(widthAndHeight, widthAndHeight).into(iv);

            mLlFocusAddContainer.addView(iv);
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
        mLlFocusAddContainer.addView(iv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOutMessage();
    }

    /**
     * 当点击返回时/关闭时调用保存
     */
    private void mOutMessage() {
        //先清空表，再将编写好的数据添加到数据库中
        table_bean_huose_add.clear();


        if (!TextUtils.isEmpty(mRoomData.getAddrDetali())) {
            table_bean_huose_add.insert(mRoomData);
        }
        for (int i = 0; i < focusDataList.size(); i++) {
            if (focusDataList.get(i).isCheck())
                table_bean_listData_add.insert(focusDataList.get(i));
        }

        List<Sql_Table_Photo.mPhotoClass> photoClass = table_photo.select();
        if (photoClass.size() <= 0) {
            for (int i = 0; i < mShowAddress.size(); i++) {
                table_photo.insert(mShowAddress.get(i), "focus");
            }
        }
        for (int j = 0; j < photoClass.size(); j++) {
            if (photoClass.get(j).getmStyle().equals("focus")) {
                break;
            }
            if (j == photoClass.size() - 1) {
                for (int i = 0; i < mShowAddress.size(); i++) {
                    table_photo.insert(mShowAddress.get(i), "focus");
                }
            }
        }
    }
}
