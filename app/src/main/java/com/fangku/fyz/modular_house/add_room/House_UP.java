package com.fangku.fyz.modular_house.add_room;/**
 * Created by 67343 on 2016/9/12.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.base.BaseResponse;
import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.DensityUtil;
import com.fangku.commonlibrary.utils.HttpUtil;
import com.fangku.commonlibrary.utils.PhotoUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.imageutil.ImageUtil;
import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.Bean_Callback;
import com.fangku.commonlibrary.widget.NoScrollGridView;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.bean.Bean_ListData;
import com.fangku.fyz.constant.Static_Login;
import com.fangku.fyz.constant.Url_final;
import com.fangku.fyz.modular_house.Fragment_House;
import com.fangku.fyz.modular_house.adapter.Adapter_GridView_DataZhouBian;
import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;
import com.fangku.fyz.modular_house.bean.Bean_UPHouse;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.util.Only_CallBack;
import com.fangku.fyz.widget.Dialog_Show;
import com.fangku.fyz.widget.UpLodionImages;
import com.kyleduo.switchbutton.SwitchButton;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 房产维护
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class House_UP extends MyBaseActivity implements House_Add_Dialog.OnItemData {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_up_address)
    TextView mTvUpAddress;
    @Bind(R.id.tv_up_address_sheng)
    TextView mTvUpAddressSheng;
    @Bind(R.id.et_house_louname)
    EditText mEtHouseLouname;
    @Bind(R.id.tv_up_louchengNums)
    TextView mTvUpLouchengNums;
    @Bind(R.id.sb_up_dianti)
    SwitchButton mSbUpDianti;
    @Bind(R.id.pb_up)
    ProgressBar mPbUp;
    @Bind(R.id.iv_up_shanjiao)
    ImageView mIvUpShanjiao;
    @Bind(R.id.nsgv_up_zoubian)
    NoScrollGridView mNsgvHouseZoubian;
    @Bind(R.id.ll_up_add_container)
    LinearLayout mLlUpAddContainer;

    //房产变量
    private String tHouseID;
    private boolean haveHouseID = false;

    //图片相关
    private ArrayList<String> mOldImage = new ArrayList<>();
    public ArrayList<String> mShowAddress = new ArrayList<>();
    private static final int REQUESTIMGCODE = 0x013;
    private static final int SHOWADDRESSMAX = 3;
    UpLodionImages mUpLodionImages;//图片上传工具

    //周边相关
    Adapter_GridView_DataZhouBian upDataListAdapter;
    List<Bean_ListData> upDataList = new ArrayList<>();
    List<Bean_ListData> data;
    List<String> mitte = new ArrayList<>();

    //数量选择
    House_Add_Dialog dialog;

    //房产信息
    private Bean_Huose_Add mRoomData = new Bean_Huose_Add();

    @Override
    public int bindLayout() {
        return R.layout.house_up;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setText("房产维护");

        thisGetHouseData();

        upDataListAdapter = new Adapter_GridView_DataZhouBian(this, upDataList, false);
        mNsgvHouseZoubian.setAdapter(upDataListAdapter);

    }

    @Override
    public void getData() {

    }

    @OnClick({R.id.ll_up_address_sheng, R.id.bt_up_delete, R.id.ll_up_add_container, R.id.ll_up_louchengNums, R.id.btn_back, R.id.ll_up_ZuoBianPeiTao, R.id.bt_up_next, R.id.bt_up_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_up_delete:
                if (haveHouseID) {
                    Dialog_Show.showTwoButton(mContext, "警告", "是否删除  " + mEtHouseLouname.getText().toString() + "  ?", "否", "是", new Only_CallBack() {
                        @Override
                        public void isSuccess(String result) {

                            if (result.equals("2")) {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("sysId", tHouseID);
                                PostUtil postUtil=new PostUtil();
                                postUtil.Post_Bean(Url_final.DELETE_HOUSE, map)
                                        .execute(new Bean_Callback<BaseResponse>(mContext) {
                                            @Override
                                            protected void onSuccess_Code200(BaseResponse response, String message) {

                                                Fragment_House.isUpdate = true;
                                                Static_Login.updateHouseName(mContext, result1 -> finish());
                                            }

                                            @Override
                                            protected void onOver() {

                                            }
                                        });
                            }
                        }
                    });
                } else {
                    ToastUtil.showShort(mContext, "您还未选择房产！！");
                }
                break;


            case R.id.ll_up_address_sheng:
                if (haveHouseID) {
                    Intent intent = new Intent(House_UP.this, House_Get_Address.class);
                    this.startActivityForResult(intent, CONTEXT_INCLUDE_CODE);
                } else {
                    ToastUtil.showShort(mContext, "您还未选择房产！！");
                }
                break;
            case R.id.ll_up_louchengNums:
                if (haveHouseID) {
                    dialog = new House_Add_Dialog();
                    dialog.SetOnItemListent(this);
                    dialog.show(this, 1);
                } else {
                    ToastUtil.showShort(mContext, "您还未选择房产！！");
                }
                break;
            case R.id.ll_up_add_container:
                if (haveHouseID) {
                    selectImg();
                } else {
                    ToastUtil.showShort(mContext, "您还未选择房产！！");
                }
                break;
            case R.id.ll_up_ZuoBianPeiTao:
                if (haveHouseID) {
                    if (mNsgvHouseZoubian.getVisibility() != View.VISIBLE) {
                        mNsgvHouseZoubian.setVisibility(View.VISIBLE);
                        mIvUpShanjiao.setImageResource(R.mipmap.xiala_gray_icon_fang);
                        if (data == null) {
                            mPbUp.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mNsgvHouseZoubian.setVisibility(View.GONE);
                        mIvUpShanjiao.setImageResource(R.mipmap.xiala_gray_icon);
                    }
                } else {
                    ToastUtil.showShort(mContext, "您还未选择房产！！");
                }
                break;
            case R.id.bt_up_next:
                if (haveHouseID) {
                    mCreadData();
                } else {
                    ToastUtil.showShort(mContext, "您还未选择房产！！");
                }
                break;
            case R.id.bt_up_other:
                thisGetHouseData();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    /**
     * 为修改房产信息准备数据
     */
    private void mCreadData() {
        if (TextUtils.isEmpty(mEtHouseLouname.getText().toString())) {
            ToastUtil.showShort(this, "请输入要修改的楼产名称！");
            return;
        }
        mRoomData.setHouseName(mEtHouseLouname.getText().toString());

        if (TextUtils.isEmpty(mRoomData.getHouseLevel())) {
            ToastUtil.showShort(this, "请选择要修改的楼层数量！");
            return;
        }
        if (mSbUpDianti.isChecked()) {
            mRoomData.setIsLift("1");
        } else {
            mRoomData.setIsLift("0");
        }
        mLoadingDialog.show(this, "正在修改房产信息 。。。");
        Map<String, String> house = new HashMap<>();

        house.put("sysId", tHouseID);
        house.put("userid", UserDataUtil.getUserInfo().getData().getUserId());
        house.put("addrDetail", mRoomData.getAddrDetali());
        house.put("region", mRoomData.getRegion());
        house.put("houseName", mRoomData.getHouseName());
        house.put("houseLevel", mRoomData.getHouseLevel() + "");
        house.put("roomNumber", "5");
        house.put("lng", mRoomData.getLng().toString());
        house.put("lat", mRoomData.getLat().toString());
        house.put("houseType", mRoomData.getHouseType());
        house.put("rentType", mRoomData.getRentType() + "");
        house.put("isLift", mRoomData.getIsLift());
        house.put("emptyNumber", "5");
        String matingFouse = "";
        for (int i = 0; i < upDataList.size(); i++) {
            if (upDataList.get(i).isCheck()) {
                matingFouse += upDataList.get(i).getName() + "|";
            }
        }
        house.put("arroundSupport", matingFouse);

        if (mOldImage.size() > 0) {
            house.put("image1", mOldImage.get(0));
            house.put("image2", mOldImage.get(1));
            house.put("image3", mOldImage.get(2));
            doIntent(house);
        } else if (mShowAddress.size() > 0) {
            //添加房产图片
            String imgs2 = "image";

            mUpLodionImages = new UpLodionImages(this, mShowAddress.size());

            for (int i = 0; i < mShowAddress.size(); i++) {
                mUpLodionImages.doUp(mShowAddress.get(i), i + 1);
                mUpLodionImages.setImageUPLinstent(new UpLodionImages.ImageUPLinstent() {
                    @Override
                    public void getImgaeID(String imagID, boolean[] isLast, int position) {
                        house.put(imgs2 + String.valueOf(position), imagID);
                        for (int i = 0; i < isLast.length; i++) {
                            if (!isLast[i]) {
                                break;
                            }
                            if (i + 1 == isLast.length) {
                                doIntent(house);
                            }
                        }
                    }
                });
            }
        } else {
            doIntent(house);
        }


    }

    /**
     * 进行修改网络访问
     *
     * @param house
     */
    private void doIntent(Map<String, String> house) {
        PostUtil postUtil=new PostUtil();
        postUtil.Post_Bean(Url_final.UPDataHouse, house).execute(new Bean_Callback<BaseResponse>(mContext) {
            @Override
            protected void onSuccess_Code200(BaseResponse response, String message) {
                Static_Login.updateHouseName(House_UP.this, new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        Log.i("test", "re:" + result);
                    }
                });
                Dialog_Show.showOneButton(mContext, "提示！", "修改成功，点击确定返回。", "确定", new Only_CallBack() {
                    @Override
                    public void isSuccess(String result) {
                        finish();
                    }
                });
            }

            @Override
            protected void onOver() {
                mLoadingDialog.cancel();
            }
        });
    }

    /**
     * 获取需要修改的房产ID
     */

    private void thisGetHouseData() {

        new Handler().postDelayed(() -> {
            Dialog_Show.showList(this, new Only_CallBack() {
                @Override
                public void isSuccess(String result) {
                    tHouseID = null;
                    tHouseID = MyApplication.houseSysID[Integer.valueOf(result)];
                    if (!TextUtils.isEmpty(tHouseID)) {
                        haveHouseID = true;
                        getRoomData();
                    } else {
                        haveHouseID = false;
                    }
                    data = null;
                }
            });
        }, 500);

    }

    private void selectImg() {
        PhotoUtil.openAlbumSelectPhotos(this, mShowAddress, SHOWADDRESSMAX, REQUESTIMGCODE);
    }

    /**
     * 加载周边
     */
    private void addZhoubianData() {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doPost(Url_final.GET_BASELIST + "AroundSupport/" + "false", new JsonCallBack() {
            @Override
            public void onSuccess(String result) {
                mPbUp.setVisibility(View.GONE);
                if (result == null) {
                } else {
                    upDataList.clear();
                    data = JsonMananger.jsonToList(result, Bean_ListData.class);
                    upDataList.addAll(data);
                    for (int k = 0; k < mitte.size(); k++) {
                        for (int i = 0; i < upDataList.size(); i++) {
                            if (upDataList.get(i).getName().equals(mitte.get(k))) {
                                upDataList.get(i).setIsCheck(true);
                            }
                        }
                    }
                    upDataListAdapter.notifyDataSetInvalidated();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mPbUp.setVisibility(View.GONE);
                ToastUtil.showShort(getApplicationContext(), R.string.will_dopost_onFailed);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTIMGCODE && data != null) {
            mShowAddress = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (mShowAddress.size() > 0) {
                mOldImage.clear();
            }
            setImags(mShowAddress);
        }
        if (CONTEXT_INCLUDE_CODE == requestCode) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                mRoomData.setRegion(bundle.getString("Region"));
                mRoomData.setAddrDetali(bundle.getString("addrDetali"));
                mRoomData.setLng(bundle.getDouble("Lng"));
                mRoomData.setLat(bundle.getDouble("Lat"));
                mTvUpAddress.setText(mRoomData.getAddrDetali());
                mTvUpAddressSheng.setText(mRoomData.getRegion());
            }
        }
    }

    @Override
    public void getNums(int nums, int name) {
        switch (name) {
            case 1:
                mTvUpLouchengNums.setText(String.valueOf(nums) + "层");
                mRoomData.setHouseLevel(String.valueOf(nums));
                break;
            case 2:
                break;
        }
    }

    /**
     * 获取需要需改的房产信息
     */
    public void getRoomData() {
        mLoadingDialog.show(this, "正在加载房产信息。。。。");
        Map<String, String> map = new HashMap<>();
        map.put("houseId", tHouseID);
        PostUtil postUtil=new PostUtil();
        postUtil.Post_Bean(Url_final.GetSingleHouse, map).execute(new Bean_Callback<Bean_UPHouse>(mContext) {
            @Override
            protected void onSuccess_Code200(Bean_UPHouse up, String message) {
                mRoomData.setAddrDetali(up.getData().getAddrDetail());
                mRoomData.setIsLift(up.getData().getIsLift());
                mRoomData.setHouseName(up.getData().getHouseName());
                mRoomData.setEmptyNumber(String.valueOf(up.getData().getEmptyNumber()));
                mRoomData.setHouseLevel(String.valueOf(up.getData().getHouseLevel()));
                mRoomData.setHouseType(up.getData().getHouseType());
                mRoomData.setLat(up.getData().getLat());
                mRoomData.setLng(up.getData().getLng());
                mRoomData.setImage1(String.valueOf(up.getData().getImage1()));
                mRoomData.setImage2(String.valueOf(up.getData().getImage2()));
                mRoomData.setImage3(String.valueOf(up.getData().getImage3()));
                mRoomData.setRegion(up.getData().getRegion());
                mRoomData.setRoomNumber(String.valueOf(up.getData().getRoomNumber()));
                mRoomData.setArroundSupportList(up.getData().getArroundSupport());
                mRoomData.setRentType(up.getData().getRentType());
                if (!TextUtils.isEmpty(mRoomData.getArroundSupportList())) {
                    String[] str = mRoomData.getArroundSupportList().split("|");
                    int top = 0;
                    mitte.clear();
                    for (int i = 0; i < str.length; i++) {
                        if (str[i].equals("|")) {
                            String str2 = "";
                            for (int j = top; j < i; j++) {
                                str2 += str[j];
                            }
                            top = i + 1;
                            mitte.add(str2.trim());
                        }
                    }
                }
                addZhoubianData();
                upView(mRoomData);
                mLoadingDialog.cancel();
            }

            @Override
            protected void onOver() {
                mLoadingDialog.cancel();
            }
        });
    }

    /**
     * 将Image加载到控件当中
     *
     * @param mShow
     */
    private void setImags(ArrayList<String> mShow) {
        mLlUpAddContainer.removeAllViews();
        for (int i = 0; i < mShow.size(); i++) {
            String imgPath = mShow.get(i);
            ImageView iv = new ImageView(this);
            int widthAndHeight = DensityUtil.dp2px(this, 50);
            int padding = DensityUtil.dp2px(this, 3);
            iv.setPadding(padding, padding, 0, padding);
            final int finalI = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(House_UP.this, House_Add_Preview.class);
                    intent.putStringArrayListExtra("pics", mShow);
                    intent.putExtra("index", finalI);
                    House_UP.this.startActivity(intent);
                }
            });

            Glide.with(this).load(new File(imgPath)).override(widthAndHeight, widthAndHeight).into(iv);
            mLlUpAddContainer.addView(iv);
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
        mLlUpAddContainer.addView(iv);
    }

    /**
     * 更新界面
     *
     * @param data
     */
    private void upView(Bean_Huose_Add data) {
        mTvUpAddress.setText(data.getAddrDetali());
        mTvUpAddressSheng.setText(data.getRegion());
        mEtHouseLouname.setText(data.getHouseName());
        mTvUpLouchengNums.setText(data.getHouseLevel() + "层");
        if ("1".equals(data.getIsLift())) {
            mSbUpDianti.setChecked(true);
        } else {
            mSbUpDianti.setChecked(false);
        }
        ArrayList<String> mImag = new ArrayList<>();
        if (!TextUtils.isEmpty(data.getImage1())) {
            mImag.add(data.getImage1());
        }
        if (!TextUtils.isEmpty(data.getImage2())) {
            mImag.add(data.getImage2());
        }
        if (!TextUtils.isEmpty(data.getImage3())) {
            if (!data.getImage3().equals("null")) {
                mImag.add(data.getImage3());
            }
        }
        mOldImage.addAll(mImag);
        mLlUpAddContainer.removeAllViews();
        for (int i = 0; i < mImag.size(); i++) {
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
            ImageUtil.loadSmallImage(mImag.get(i), iv);
            mLlUpAddContainer.addView(iv);
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
        mLlUpAddContainer.addView(iv);
    }
}
