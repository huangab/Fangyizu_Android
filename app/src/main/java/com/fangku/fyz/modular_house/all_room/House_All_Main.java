package com.fangku.fyz.modular_house.all_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.R;
import com.fangku.fyz.modular_house.adapter.Adapter_house_all_expandablelist;
import com.fangku.fyz.modular_house.bean.Bean_House_Type_Room;
import com.fangku.fyz.util.MyBaseActivity;
import com.fangku.fyz.widget.Dialog_Show;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 点击所有房间之后进入的画面
 * Created by   jie.wang
 * Date: 2016/8/3
 * Time: 15:55
 */
public class House_All_Main extends MyBaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_house_all_bar1)
    TextView mTvHouseAllBar1;
    @Bind(R.id.view_house_all_bar1)
    View mViewHouseAllBar1;
    @Bind(R.id.rl_house_all_bar1)
    RelativeLayout mRlHouseAllBar1;
    @Bind(R.id.tv_house_all_bar2)
    TextView mTvHouseAllBar2;
    @Bind(R.id.view_house_all_bar2)
    View mViewHouseAllBar2;
    @Bind(R.id.rl_house_all_bar2)
    RelativeLayout mRlHouseAllBar2;
    @Bind(R.id.tv_house_all_bar3)
    TextView mTvHouseAllBar3;
    @Bind(R.id.view_house_all_bar3)
    View mViewHouseAllBar3;
    @Bind(R.id.rl_house_all_bar3)
    RelativeLayout mRlHouseAllBar3;
    @Bind(R.id.elv_house_all_main)
    ExpandableListView mElv;
    @Bind(R.id.tv_all_main_not_room)
    TextView mTvAllMainNotRoom;
    @Bind(R.id.iv_title_right)
    ImageView mIvTitleRight;

    private String houseID = "199";
    private String houseName = "我的房屋";
    public static boolean isNeedUpdate = false;
    private List<Bean_House_Type_Room.DataBean> data = new ArrayList<>();
    private Adapter_house_all_expandablelist mAdapter;

    public static List<Bean_House_Type_Room.DataBean> Data1 = new ArrayList<>();
    public static List<Bean_House_Type_Room.DataBean> Data2 = new ArrayList<>();
    public static List<Bean_House_Type_Room.DataBean> Data3 = new ArrayList<>();
    private House_ALL_Main_Helper helper;


    public static void lanuch(Context context, String houseID, String houseName) {
        Intent intent = new Intent(context, House_All_Main.class);
        intent.putExtra("houseId", houseID);
        intent.putExtra("houseName", houseName);
        context.startActivity(intent);
    }


    @Override
    public int bindLayout() {
        return R.layout.house_all_main;
    }

    @Override
    public void createActivity(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mTvTitle.setTextSize(17);
        mIvTitleRight.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        houseName = intent.getStringExtra("houseName");
        houseID = intent.getStringExtra("houseId");

        isNeedUpdate = true;

        if (houseName == null) {
            mTvTitle.setText("我的房屋");
        } else {
            mTvTitle.setText(houseName);
        }

        //初始化帮助类
        helper = new House_ALL_Main_Helper(mContext,  mTvHouseAllBar1, mTvHouseAllBar2, mTvHouseAllBar3, mLoadingDialog);

        //2级Listview 初始化
        mElv.setGroupIndicator(null);
        mAdapter = new Adapter_house_all_expandablelist(House_All_Main.this, mTvTitle.getText().toString(),
                data);
        mElv.setAdapter(mAdapter);
        mElv.setOnGroupExpandListener(groupPosition -> {

            //当点击某一项 关闭其他项
            for (int i = 0; i < mAdapter.getGroupCount(); i++) {
                if (groupPosition != i) {
                    mElv.collapseGroup(i);
                }
            }
        });

    }


    @Override
    public void getData() {

    }


    @Override
    public void onResume() {
        super.onResume();

        if (isNeedUpdate) {
            mLoadingDialog.show(mContext);
            isNeedUpdate = false;
            Log.i("sss", "onResume: " + "我进来了2");
            data.clear();


            helper.getListData_1(houseID, result -> {
                data.addAll(Data1);
                mAdapter.notifyDataSetChanged();
                colorChange(1);

            });
            helper.getListData_2(houseID, result -> {

            });
            helper.getListData_3(houseID, result -> {

            });


        }
    }

    @OnClick({R.id.btn_back, R.id.iv_title_right, R.id.rl_house_all_bar1, R.id.rl_house_all_bar2, R.id.rl_house_all_bar3, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:

                if (MyApplication.stringItems.length == 0) {
                    ToastUtil.showShort(mContext, "您尚未添加房产");
                    return;
                }

                Dialog_Show.showList(House_All_Main.this, result -> {
                    mTvTitle.setText(MyApplication.stringItems[Integer.valueOf(result)]);//顶部选择房屋弹出框
                    houseID = MyApplication.houseSysID[Integer.valueOf(result)];
                    isNeedUpdate = true;
                    onResume();
                });
                break;
            case R.id.iv_title_right://跟上面的一样的操作
                if (MyApplication.stringItems.length == 0) {
                    ToastUtil.showShort(mContext, "您尚未添加房产");
                    return;
                }

                Dialog_Show.showList(House_All_Main.this, result -> {
                    mTvTitle.setText(MyApplication.stringItems[Integer.valueOf(result)]);//顶部选择房屋弹出框
                    houseID = MyApplication.houseSysID[Integer.valueOf(result)];
                    isNeedUpdate = true;
                    onResume();
                });
                break;


            case R.id.btn_back:
                finish();

                break;
            case R.id.rl_house_all_bar1:

                data.clear();
                data.addAll(Data1);
                colorChange(1);
                mAdapter.notifyDataSetChanged();


                break;
            case R.id.rl_house_all_bar2:


                data.clear();
                data.addAll(Data2);
                colorChange(2);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_house_all_bar3:
                data.clear();
                data.addAll(Data3);
                colorChange(3);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }


    //点击bar 对应颜色变换 并且更改list数据  传入为123

    private void colorChange(int a) {
        if (data.size() == 0) {
            mTvAllMainNotRoom.setVisibility(View.VISIBLE);
        } else {
            mTvAllMainNotRoom.setVisibility(View.GONE);
        }

        mTvHouseAllBar1.setTextColor(ContextCompat.getColor(mContext, R.color.no_activation));
        mTvHouseAllBar2.setTextColor(ContextCompat.getColor(mContext, R.color.no_activation));
        mTvHouseAllBar3.setTextColor(ContextCompat.getColor(mContext, R.color.no_activation));
        mViewHouseAllBar1.setBackgroundResource(R.color.white);
        mViewHouseAllBar2.setBackgroundResource(R.color.white);
        mViewHouseAllBar3.setBackgroundResource(R.color.white);


        switch (a) {
            case 1:
                mTvHouseAllBar1.setTextColor(ContextCompat.getColor(mContext, R.color.activation));
                mViewHouseAllBar1.setBackgroundResource(R.color.activation);

                mRlHouseAllBar1.setEnabled(false);
                mRlHouseAllBar2.setEnabled(true);
                mRlHouseAllBar3.setEnabled(true);
                break;
            case 2:
                mTvHouseAllBar2.setTextColor(ContextCompat.getColor(mContext, R.color.activation));
                mViewHouseAllBar2.setBackgroundResource(R.color.activation);
                mRlHouseAllBar1.setEnabled(true);
                mRlHouseAllBar2.setEnabled(false);
                mRlHouseAllBar3.setEnabled(true);

                break;
            case 3:
                mTvHouseAllBar3.setTextColor(ContextCompat.getColor(mContext, R.color.activation));
                mViewHouseAllBar3.setBackgroundResource(R.color.activation);
                mRlHouseAllBar1.setEnabled(true);
                mRlHouseAllBar2.setEnabled(true);
                mRlHouseAllBar3.setEnabled(false);
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
        Data1.clear();
        Data2.clear();
        Data3.clear();

    }
}